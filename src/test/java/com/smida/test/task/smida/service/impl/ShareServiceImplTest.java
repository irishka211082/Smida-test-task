package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.domain.Share;
import com.smida.test.task.smida.domain.Status;
import com.smida.test.task.smida.exceptions.NoShareException;
import com.smida.test.task.smida.exceptions.NoSharesException;
import com.smida.test.task.smida.repository.ShareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ShareServiceImplTest {

    @InjectMocks
    private ShareServiceImpl shareService;

    @Mock
    private ShareRepository shareRepository;

    @Mock
    private ShareHistServiceImpl shareHistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createShare() {
        Share expectedShare = prepareShareFromRepository();

        when(shareRepository.save(any(Share.class))).thenReturn(prepareShareFromRepository());
        Share actualShare = shareService.create(prepareShareForCreating());

        assertEquals(expectedShare.getReleaseDate(), actualShare.getReleaseDate());
        assertEquals(expectedShare.getSharesNumber(), actualShare.getSharesNumber());
        assertEquals(expectedShare.getErdpou(), actualShare.getErdpou());
        assertEquals(expectedShare.getNominalValue(), actualShare.getNominalValue());
        assertEquals(expectedShare.getTotalNominalValue(), expectedShare.getTotalNominalValue());
        assertEquals(expectedShare.getId(), actualShare.getId());
        assertEquals(expectedShare.getStatus(), actualShare.getStatus());
    }

    @Test
    public void findById() {
        Share expectedShare = prepareShareFromRepository();

        when(shareRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(prepareShareFromRepository()));
        Share actualShare = shareService.findById(1L);

        assertEquals(expectedShare.getId(), actualShare.getId());
        assertEquals(expectedShare.getComment(), actualShare.getComment());
    }

    @Test
    public void findByIdWhenIdDoesntExists() throws NoShareException {
        when(shareRepository.findById(2L)).thenThrow(new NoShareException());

        assertThrows(NoShareException.class, () -> {
            shareService.findById(2L);
        });
    }

    @Test
    public void getAllShares() {
        when(shareRepository.findAll()).thenReturn(prepareSharesFromRepository());
        List<Share> actualShares = shareService.getAllShares();

        assertEquals(actualShares.size(), 3);
    }

    @Test
    public void getAllSharesWhenDBIsEmpty() {
        when(shareRepository.findAll()).thenThrow(new NoSharesException());

        assertThrows(NoSharesException.class, () -> {
            shareService.getAllShares();
        });
    }

    @Test
    public void getAllSharesByErdpou() {
        when(shareRepository.findAllByErdpou(10010010)).thenReturn(prepareSharesFromRepository());
        List<Share> actualShares = shareService.getAllShares(10010010);

        assertEquals(actualShares.size(), 3);
    }

    @Test
    public void getAllSharesByErdpouWhenItDoesntExist() {

        when(shareRepository.findAllByErdpou(10010010)).thenThrow(new NoSharesException());

        assertThrows(NoSharesException.class, () -> {
            shareService.getAllShares(10010010);
        });
    }

    @Test
    public void getAllSharesByStatus() {
        when(shareRepository.findAllByStatus(Status.ACTIVE)).thenReturn(prepareSharesFromRepository());
        List<Share> actualShares = shareService.getAllShares(Status.ACTIVE);

        assertEquals(actualShares.size(), 3);
    }

    @Test
    public void getAllSharesByStatusWhenItDoesntExist() {

        when(shareRepository.findAllByStatus(Status.DELETED)).thenThrow(new NoSharesException());

        assertThrows(NoSharesException.class, () -> {
            shareService.getAllShares(Status.DELETED);
        });
    }

    private List<Share> prepareSharesFromRepository() {
        Share share = prepareShareFromRepository();

        return new ArrayList<>(Arrays.asList(share, share, share));
    }

    private Share prepareShareForCreating() {
        return Share.builder()
                .comment("Amazon")
                .sharesNumber(10)
                .erdpou(10010010)
                .nominalValue(100.01)
                .releaseDate(new Timestamp(20001004))
                .build();
    }

    private Share prepareShareFromRepository() {
        return Share.builder()
                .comment("Amazon")
                .sharesNumber(10)
                .erdpou(10010010)
                .nominalValue(100.01)
                .totalNominalValue(1000.10)
                .releaseDate(new Timestamp(20001004))
                .id(1)
                .status(Status.ACTIVE)
                .build();
    }
}