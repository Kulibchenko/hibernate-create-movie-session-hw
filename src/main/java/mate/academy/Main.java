package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);
        CinemaHall blackHoll = new CinemaHall();
        blackHoll.setCapacity(60);
        blackHoll.setDescription("BlackHoll");
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        cinemaHallService.add(blackHoll);
        MovieSession fastAndFuriosInBlackHoll = new MovieSession();
        fastAndFuriosInBlackHoll.setCinemaHall(blackHoll);
        fastAndFuriosInBlackHoll.setMovie(fastAndFurious);
        fastAndFuriosInBlackHoll.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(fastAndFuriosInBlackHoll);
        System.out.println(LocalDate.now());
        movieSessionService.findAvailableSessions(1L, LocalDate.now()).forEach(System.out::println);
        System.out.println(movieService.get(fastAndFurious.getId()));
        System.out.println(cinemaHallService.get(blackHoll.getId()));
        System.out.println(movieService.get(fastAndFuriosInBlackHoll.getId()));
    }
}
