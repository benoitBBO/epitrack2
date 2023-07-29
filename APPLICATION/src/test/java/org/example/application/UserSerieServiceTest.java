package org.example.application;

import org.example.application.util.CalculServiceImpl;
import org.example.application.util.ICalculService;
import org.example.domaine.catalog.Episode;
import org.example.domaine.userselection.UserEpisode;
import org.example.infrastructure.repository.ISerieRepository;
import org.example.infrastructure.repository.IUserEpisodeRepository;
import org.example.infrastructure.repository.IUserSeasonRepository;
import org.example.infrastructure.repository.IUserSerieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UserSerieServiceImpl.class, IUserSerieRepository.class,
                           SerieServiceImpl.class, ISerieRepository.class,
                            UserSeasonServiceImpl.class, IUserSeasonRepository.class,
                            UserEpisodeServiceImpl.class, IUserEpisodeRepository.class,
                            CalculServiceImpl.class})
public class UserSerieServiceTest {
    @MockBean
    IUserSerieRepository userSerieRepository;
    @MockBean
    ISerieRepository serieRepository;
    @MockBean
    IUserSeasonRepository userSeasonRepository;
    @MockBean
    IUserEpisodeRepository userEpisodeRepository;
    @Autowired
    IUserSerieService userSerieService;
    @Autowired
    ISerieService serieService;
    @Autowired
    IUserSeasonService userSeasonService;
    @Autowired
    IUserEpisodeService userEpisodeService;
    @Autowired
    ICalculService calculService;


    @Test
    public void test_should_return_sortedEpisodeList_when_sortByEpisodeNumber(){
        Episode episode1 = new Episode("num1", 1, "test", "", LocalDate.now());
        Episode episode2 = new Episode("num2", 2, "test", "", LocalDate.now());
        Episode episode3 = new Episode("num3", 3, "test", "", LocalDate.now());
        Episode episode4 = new Episode("num4", 4, "test", "", LocalDate.now());
        Episode episode5 = new Episode("num5", 5, "test", "", LocalDate.now());

        UserEpisode userEpisode1 = new UserEpisode();
        userEpisode1.setEpisode(episode1);
        UserEpisode userEpisode2 = new UserEpisode();
        userEpisode2.setEpisode(episode2);
        UserEpisode userEpisode3 = new UserEpisode();
        userEpisode3.setEpisode(episode3);
        UserEpisode userEpisode4 = new UserEpisode();
        userEpisode4.setEpisode(episode4);
        UserEpisode userEpisode5 = new UserEpisode();
        userEpisode5.setEpisode(episode5);


        List<UserEpisode> userEpisodeListIn = new ArrayList<>();
        userEpisodeListIn.add(userEpisode2);
        userEpisodeListIn.add(userEpisode5);
        userEpisodeListIn.add(userEpisode1);
        userEpisodeListIn.add(userEpisode4);
        userEpisodeListIn.add(userEpisode3);
        List<UserEpisode> userEpisodeListSorted = new ArrayList<>();
        userEpisodeListSorted.add(userEpisode1);
        userEpisodeListSorted.add(userEpisode2);
        userEpisodeListSorted.add(userEpisode3);
        userEpisodeListSorted.add(userEpisode4);
        userEpisodeListSorted.add(userEpisode5);

        assertThat(userSerieService.sortByEpisodeNumber(userEpisodeListIn)).isEqualTo(userEpisodeListSorted);
    }
}
