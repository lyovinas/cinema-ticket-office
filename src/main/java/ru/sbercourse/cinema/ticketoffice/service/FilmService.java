package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSearchDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmWithSessionsDTO;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmMapper;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmWithSessionsMapper;
import ru.sbercourse.cinema.ticketoffice.mapper.ReviewMapper;
import ru.sbercourse.cinema.ticketoffice.model.*;
import ru.sbercourse.cinema.ticketoffice.repository.FilmCreatorRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;
import ru.sbercourse.cinema.ticketoffice.service.userdetails.CustomUserDetails;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.MANAGER;
import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.USER;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {

    @Value("${spring.security.user.name}")
    private String adminUserName;
    private FilmCreatorRepository filmCreatorRepository;
    private FilmWithSessionsMapper filmWithSessionsMapper;
    private ReviewMapper reviewMapper;
    private UserRepository userRepository;



    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        repository = filmRepository;
        mapper = filmMapper;
    }



    public FilmDTO addFilmCreator(Long filmId, Long filmCreatorId) {
        Film film = repository.findById(filmId).orElse(null);
        FilmCreator filmCreator = filmCreatorRepository.findById(filmCreatorId).orElse(null);
        if (film != null && filmCreator != null) {
            film.getFilmCreators().add(filmCreator);
            repository.save(film);
            return mapper.toDTO(film);
        } else return null;
    }

    public void deleteFilmCreator(Long filmId, Long filmCreatorId) {
        Film film = repository.findById(filmId).orElse(null);
        FilmCreator filmCreator = filmCreatorRepository.findById(filmCreatorId).orElse(null);
        if (film != null && filmCreator != null) {
            film.getFilmCreators().remove(filmCreator);
            repository.save(film);
        }
    }

    public List<FilmCreator> getCreators(Long id) { //TODO убрать, если не используется (в FilmCreatorController)
        Film film = repository.findById(id).orElse(null);
        return film != null
                ? film.getFilmCreators().stream().toList()
                : null;
    }

    public List<FilmWithSessionsDTO> getAllWithSessions() {
        return filmWithSessionsMapper.toDTOs(repository.findAll());
    }

    public List<FilmDTO> getAllAvailable() {
        return mapper.toDTOs(((FilmRepository) repository).getAllAvailable(LocalTime.now()));
    }

    public List<FilmDTO> getAllByDate(LocalDate filmSessionStartDate) {
        List<Film> films = new ArrayList<>();
        if (!filmSessionStartDate.isBefore(LocalDate.now())) {
            films = LocalDate.now().equals(filmSessionStartDate)
                    ? ((FilmRepository) repository).getAllByDate(filmSessionStartDate, LocalTime.now())
                    : ((FilmRepository) repository).getAllByDate(filmSessionStartDate, LocalTime.of(0, 0));
        }
        return mapper.toDTOs(films);
    }

    public FilmWithSessionsDTO getWithSessionsById(Long id) {
        // если админ или менеджер, то вернуть все сеансы
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (
                authentication != null &&
                authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)
        ) {
            if (authentication.getName().equals(adminUserName)) {
                return filmWithSessionsMapper.toDTO(repository.findById(id).orElse(null));
            }
            Long userId = Long.valueOf(((CustomUserDetails) authentication.getPrincipal()).getUserId());
            User user = userRepository.findById(userId).orElse(null);
            if (user != null && user.getRole().getTitle().equals(MANAGER)) {
                return filmWithSessionsMapper.toDTO(repository.findById(id).orElse(null));
            }
        }
        // если не админ или менеджер, то вернуть актуальные сеансы
        Film film = repository.findById(id).orElse(null);
        if (film != null) {
            Set<FilmSession> filmSessions = film.getFilmSessions();
            film.setFilmSessions(
                    filmSessions.stream()
                    .filter(fs -> !fs.isDeleted())
                    .filter(fs -> {
                        if (fs.getStartDate().equals(LocalDate.now())) {
                            return fs.getStartTime().isAfter(LocalTime.now());
                        }
                        return fs.getStartDate().isAfter(LocalDate.now());
                    })
                    .collect(Collectors.toSet())
            );
            return filmWithSessionsMapper.toDTO(film);
        }
        return null;
    }

    @Override
    public FilmDTO update(FilmDTO filmDTO) {
        Film film = mapper.toEntity(filmDTO);
        Long id = film.getId();
        if (id != null) {
            Film existingFilm = repository.findById(id).orElse(null);
            if (existingFilm != null) {
                if (film.getFilmCreators() == null) {
                    film.setFilmCreators(existingFilm.getFilmCreators());
                }
                return super.update(mapper.toDTO(film));
            }
        }
        return null;
    }

    public Object findFilms(FilmSearchDTO filmSearchDTO, PageRequest pageRequest) {
        String genre = filmSearchDTO.getGenre() != null ? String.valueOf(filmSearchDTO.getGenre().ordinal()) : "%";
        Page<Film> filmPage = ((FilmRepository) repository).searchFilms(genre, filmSearchDTO.getFilmTitle(),
                filmSearchDTO.getFilmCreatorFullName(), pageRequest);
        List<FilmWithSessionsDTO> result = filmWithSessionsMapper.toDTOs(filmPage.getContent());
        return new PageImpl<>(result, pageRequest, filmPage.getTotalElements());
    }

    public List<ReviewDTO> getReviews(Long id) {
        Film film = repository.findById(id).orElse(null);
//        if (film != null) return new HashSet<>(reviewMapper.toDTOs(film.getReviews().stream().toList()));
        if (film != null) {
            List<Review> reviews = film.getReviews().stream().toList();
            // если не аутентифицирован или не админ и не менеджер, то убрать удаленные отзывы
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (
                    authentication != null &&
                            authentication.isAuthenticated() &&
                            !(authentication instanceof AnonymousAuthenticationToken)
            ) {
                if (!authentication.getName().equals(adminUserName)) {
                    Long userId = Long.valueOf(((CustomUserDetails) authentication.getPrincipal()).getUserId());
                    User user = userRepository.findById(userId).orElse(null);
                    if (user != null && user.getRole().getTitle().equals(USER)) {
                        reviews = reviews.stream().filter(r -> !r.isDeleted()).toList();
                    }
                }
            } else reviews = reviews.stream().filter(r -> !r.isDeleted()).toList();
            return reviewMapper.toDTOs(reviews).stream()
                    .sorted((a, b) -> {
                        if (a.getCreatedWhen().isBefore(b.getCreatedWhen())) return -1;
                        if (a.getCreatedWhen().isAfter(b.getCreatedWhen())) return 1;
                        return 0;
                    }).toList();
        }
        return null;
    }



    @Autowired
    public void setFilmCreatorRepository(FilmCreatorRepository filmCreatorRepository) {
        this.filmCreatorRepository = filmCreatorRepository;
    }

    @Autowired
    public void setFilmWithSessionsMapper(FilmWithSessionsMapper filmWithSessionsMapper) {
        this.filmWithSessionsMapper = filmWithSessionsMapper;
    }

    @Autowired
    public void setReviewMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
