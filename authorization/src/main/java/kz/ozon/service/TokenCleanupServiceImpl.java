package kz.ozon.service;

import kz.ozon.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static kz.ozon.constant.Constant.DATA_TIME_FORMATTER;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class TokenCleanupServiceImpl {
    private final RefreshTokenRepository refreshTokenRepository;

    @Async
    @Transactional
    @Scheduled(cron = "0 0 6 * * ?")
    public void cleanupExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        int deletedExpiredTokens = refreshTokenRepository.deleteAllByExpiryDateIsBefore(now);
        String nowString = now.format(DateTimeFormatter.ofPattern(DATA_TIME_FORMATTER));
        log.info("Today {} expired tokens were deleted: {}", nowString, deletedExpiredTokens);
    }
}