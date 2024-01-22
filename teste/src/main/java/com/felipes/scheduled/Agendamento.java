package com.felipes.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Agendamento {


    @Scheduled(cron = "0 0 9 * * *") // Run at 9:00 every day
    public void runTaskAt9() {
    }

    @Scheduled(cron = "0 0 17 * * *") // Run at 17:00 every day
    public void runTaskAt17() {

    }
}
