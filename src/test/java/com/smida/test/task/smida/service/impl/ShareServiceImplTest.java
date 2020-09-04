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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
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
    private Pageable pageableMock;

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
        Page<Share> expectedPageOfShares = new PageImpl<>(prepareSharesFromRepository());

        when(shareRepository.findAll(pageableMock)).thenReturn(expectedPageOfShares);
        Page<Share> actualPageOfShares = shareService.getAllShares(pageableMock);

        assertEquals(actualPageOfShares.stream().count(), expectedPageOfShares.stream().count());
    }

    @Test
    public void getAllSharesWhenDBIsEmpty() {
        when(shareRepository.findAll(pageableMock)).thenThrow(new NoSharesException());

        assertThrows(NoSharesException.class, () -> {
            shareService.getAllShares(pageableMock);
        });
    }

    @Test
    public void getAllSharesByErdpou() {
        Page<Share> expectedPageOfShares = new PageImpl<>(prepareSharesFromRepository());
        when(shareRepository.findAllByErdpou(10010010, pageableMock)).thenReturn(expectedPageOfShares);
        Page<Share> actualPageOfShares = shareService.getAllSharesByErdpou(10010010, pageableMock);

        assertEquals(expectedPageOfShares.stream().count(), actualPageOfShares.stream().count());
    }

    @Test
    public void getAllSharesByErdpouWhenItDoesntExist() {

        when(shareRepository.findAllByErdpou(10010010, pageableMock)).thenThrow(new NoSharesException());

        assertThrows(NoSharesException.class, () -> {
            shareService.getAllSharesByErdpou(10010010, pageableMock);
        });
    }

    @Test
    public void getAllSharesByStatus() {
        Page<Share> expectedPageOfShares = new PageImpl<>(prepareSharesFromRepository());
        when(shareRepository.findAllByStatus(Status.ACTIVE, pageableMock)).thenReturn(expectedPageOfShares);
        Page<Share> actualPageOfShares = shareService.getAllSharesByStatus(Status.ACTIVE, pageableMock);

        assertEquals(actualPageOfShares.stream().count(), expectedPageOfShares.stream().count());
    }

    @Test
    public void getAllSharesByStatusWhenItDoesntExist() {

        when(shareRepository.findAllByStatus(Status.DELETED, pageableMock)).thenThrow(new NoSharesException());

        assertThrows(NoSharesException.class, () -> {
            shareService.getAllSharesByStatus(Status.DELETED, pageableMock);
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
                .totalNominalValue(new BigDecimal(1000.10))
                .releaseDate(new Timestamp(20001004))
                .id(1)
                .status(Status.ACTIVE)
                .build();
    }
}