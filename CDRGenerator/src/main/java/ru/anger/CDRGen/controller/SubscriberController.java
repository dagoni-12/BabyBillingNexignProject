package ru.anger.CDRGen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.anger.CDRGen.repositories.SubscribersRepository;

@Controller
public class SubscriberController {

    @Autowired
    private SubscribersRepository subscribersRepository;
}