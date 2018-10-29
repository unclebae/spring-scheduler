package com.example.spring.scheduler.demoscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    final Long first_time = System.currentTimeMillis();

    private long getSecond() {
        return (System.currentTimeMillis() - first_time) / 1000;
    }

    private String getCurrentThread() {
        return Thread.currentThread().getName() + " : ";
    }

    /**
     * fixedDelay 는 태스크가 종료되고, 약 delay 타임 이후에 다시 태스크가 실행이 되도록 설정한다.
     * 아래 이전 태스크가 완료되고 나서 1초후에 태스크를 실행한다.
     */
    @Scheduled(fixedDelay = 1000)
    public void fixedDelayTask() {
        System.out.println("01 Fixed delay task - " + getCurrentThread() + getSecond());
    }

    /**
     * fixedDelayString 는 태스크가 종료되고, 약 delay 타임 이후에 다시 태스크가 실행이 되도록 설정한다.
     * 아래 이전 태스크가 완료되고 나서 1초후에 태스크를 실행한다.
     * fixedDelay 와 다른점은 해당 변수값이 스트링으로 지정된다는 차이점이 있다.
     */
    @Scheduled(fixedDelayString = "1000")
    public void fixedDelayStringTask() {
        System.out.println("02 Fixed delay string task - " + getCurrentThread() + getSecond());
    }

    /**
     * fixedRate 는 매 지정된 rate 에 해당 태스크를 수행한다.
     * 이 설정은 이전 작업이 종료되지 않아도 다음 태스크를 수행시킨다.
     *
     */
    @Scheduled(fixedRate = 1000)
    public void fixedRateTask() {
        System.out.println("03 Fixed rate task - " + getCurrentThread() + getSecond());
    }

    /**
     * fixedRateString 는 매 지정된 rate 에 해당 태스크를 수행한다.
     * 이 설정은 이전 작업이 종료되지 않아도 다음 태스크를 수행시킨다.
     * fixedRate와 차이점은 해당 변수값이 스트링으로 지정된다는 차이점이 있다.
     *
     */
    @Scheduled(fixedRateString = "1000")
    public void fixedRateStringTask() {
        System.out.println("04 Fixed rate string task - " + getCurrentThread() + getSecond());
    }

    /**
     * initialDelay 는 최초 수행될때 initialDelay 시간 이후에 해당 스케줄러 작업을 수행한다.
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void fixedDealyWithInitialDelayTask() {
        System.out.println("05 Fixed rate task with initial delay - " + getCurrentThread() + getSecond());
    }

    /**
     * 크론 형식으로 스케줄을 등록할 수 있다.
     * 초, 분, 시간, 일, 월, 요일, * : 모든값, ? : 특정 값 없음, - : 범위지정, (,) : 여러 값 지정, / : 초기치 증가치설정, L : 지정할 수 있는 범위의 마지막 값, W : 월 - 금요일 또는 가장 가까운 월/금요일, # : 몇 번째 무슨요일 2#1 => 첫 번째 월요일
     * 다음은 매분 0초에 실행된다.
     * 0 0 9 * * ? : 아무요일 매월 매일 9시 실행
     * 0 10 10 * * ? : 아무요일 매월 매일 10시 10분 0초 실행     */
    @Scheduled(cron = "0 * * * * ?")
    public void cronTask() {
        System.out.println("06 Cron task - " + getCurrentThread() + getSecond());
    }

    /**
     * 파라미터 설정을 해두고, 스케줄링을 걸어줄 수 있다.
     */
    @Scheduled(fixedDelayString = "${scheduler.fix.delay}")
    public void fixedDelayStringParameterTask() {
        System.out.println("07 Fixed Delay String Parameter 5 seconds " + getCurrentThread() + getSecond());
    }

    /**
     * 파라미터 설정을 해두고, 스케줄링을 걸어줄 수 있다.
     */
    @Scheduled(fixedRateString = "${scheduler.fix.rate}")
    public void fixedRateStringParameterTask() {
        System.out.println("08 Fixed Rate String Parameter 2 seconds " + getCurrentThread() + getSecond());
    }

    /**
     * 파라미터 설정을 해두고, 스케줄링을 걸어줄 수 있다.
     */
    @Scheduled(cron = "${scheduler.cron}")
    public void cronStringParameterTask() {
        System.out.println("09 --------------------------------------- Cron String Parameter every seconds " + getCurrentThread() + getSecond() + "\n");
    }
}
