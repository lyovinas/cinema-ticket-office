package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmExtendedDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSearchDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmCreatorMapper;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmExtendedMapper;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmMapper;
import ru.sbercourse.cinema.ticketoffice.model.*;
import ru.sbercourse.cinema.ticketoffice.repository.FilmCreatorRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;
import ru.sbercourse.cinema.ticketoffice.service.userdetails.CustomUserDetails;
import ru.sbercourse.cinema.ticketoffice.utils.FileHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.MANAGER;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {

    @Value("${spring.security.user.name}")
    private String adminUserName;
    private FilmExtendedMapper filmExtendedMapper;
    private FilmCreatorRepository filmCreatorRepository;
    private FilmCreatorMapper filmCreatorMapper;
    private UserRepository userRepository;
    private FileHelper fileHelper;



    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        repository = filmRepository;
        mapper = filmMapper;
    }



    public void create(FilmDTO filmDTO, MultipartFile file) {
        Film film = mapper.toEntity(filmDTO);
        film.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        film.setCreatedWhen(LocalDateTime.now());
        film = repository.save(film);

        String posterFileName = fileHelper.createFile(file, null, film.getId());
        film.setPosterFileName(posterFileName);
        repository.save(film);
    }

    public void update(FilmDTO filmDTO, MultipartFile file) {
        String posterFileName = fileHelper.createFile(file, filmDTO.getPosterFileName(), filmDTO.getId());
        filmDTO.setPosterFileName(posterFileName);
        update(filmDTO);
    }

    @Override
    public FilmDTO update(FilmDTO filmDTO) {
        Long id = filmDTO.getId();
        if (id != null) {
            Film existingFilm = repository.findById(id).orElse(null);
            if (existingFilm != null) {
                existingFilm.setTitle(filmDTO.getTitle());
                existingFilm.setReleaseYear(filmDTO.getReleaseYear());
                existingFilm.setCountry(filmDTO.getCountry());
                existingFilm.setGenre(filmDTO.getGenre());
                existingFilm.setDescription(filmDTO.getDescription());
                existingFilm.setPosterFileName(filmDTO.getPosterFileName());
                existingFilm.setUpdatedWhen(LocalDateTime.now());
                existingFilm.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                return mapper.toDTO(repository.save(existingFilm));
            }
        }
        return null;
    }

    @Override
    public void softDelete(Long id) {
        Film film = repository.findById(id).orElse(null);
        if (film != null) {
            film.setDeleted(true);
            film.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            film.setDeletedWhen(LocalDateTime.now());
            repository.save(film);
        }
    }

    @Override
    public void restore(Long id) {
        Film film = repository.findById(id).orElse(null);
        if (film != null) {
            film.setDeleted(false);
            film.setDeletedBy(null);
            film.setDeletedWhen(null);
            repository.save(film);
        }
    }

    public FilmExtendedDTO getExtendedById(Long id) {
        Film film = repository.findById(id).orElse(null);
        if (film != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (
                    authentication != null &&
                            authentication.isAuthenticated() &&
                            !(authentication instanceof AnonymousAuthenticationToken)
            ) {
                if (authentication.getName().equals(adminUserName)) {
                    return filmExtendedMapper.toDTO(film);
                } else {
                    Long userId = Long.valueOf(((CustomUserDetails) authentication.getPrincipal()).getUserId());
                    User user = userRepository.findById(userId).orElse(null);
                    if (user != null && user.getRole().getTitle().equals(MANAGER)) {
                        return filmExtendedMapper.toDTO(film);
                    }
                }
            }

            Set<FilmCreator> filmCreators = film.getFilmCreators();
            filmCreators = filmCreators.stream()
                    .filter(fc -> !fc.isDeleted())
                    .collect(Collectors.toSet());
            film.setFilmCreators(filmCreators);

            Set<FilmSession> filmSessions = film.getFilmSessions();
            filmSessions = filmSessions.stream()
                    .filter(fs -> !fs.isDeleted())
                    .filter(fs -> {
                        if (fs.getStartDate().equals(LocalDate.now())) {
                            return fs.getStartTime().isAfter(LocalTime.now());
                        }
                        return fs.getStartDate().isAfter(LocalDate.now());
                    })
                    .collect(Collectors.toSet());
            film.setFilmSessions(filmSessions);

            Set<Review> reviews = film.getReviews();
            reviews = reviews.stream()
                    .filter(r -> !r.isDeleted())
                    .collect(Collectors.toSet());
            film.setReviews(reviews);

            return filmExtendedMapper.toDTO(film);
        }
        return null;
    }

    public List<FilmCreatorDTO> getFilmCreators(Long id) {
        Film film = repository.findById(id).orElse(null);
        return film != null
                ? filmCreatorMapper.toDTOs(film.getFilmCreators().stream().toList()).stream().sorted().toList()
                : null;
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

    public List<FilmExtendedDTO> findFilms(FilmSearchDTO filmSearchDTO) {
        String genre = filmSearchDTO.getGenre() != null ? String.valueOf(filmSearchDTO.getGenre().ordinal()) : "%";
        List<Film> films = ((FilmRepository) repository).searchFilms(genre, filmSearchDTO.getFilmTitle(),
                filmSearchDTO.getFilmCreatorFullName());
        return filmExtendedMapper.toDTOs(films);
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



    @Autowired
    public void setFilmExtendedMapper(FilmExtendedMapper filmExtendedMapper) {
        this.filmExtendedMapper = filmExtendedMapper;
    }

    @Autowired
    public void setFilmCreatorRepository(FilmCreatorRepository filmCreatorRepository) {
        this.filmCreatorRepository = filmCreatorRepository;
    }

    @Autowired
    public void setFilmCreatorMapper(FilmCreatorMapper filmCreatorMapper) {
        this.filmCreatorMapper = filmCreatorMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setFileHelper(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }
}
