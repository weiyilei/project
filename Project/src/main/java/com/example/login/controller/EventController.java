package com.example.login.controller;


import com.example.login.entity.Event;
import com.example.login.repository.EventRepository;
import com.example.login.util.RandomId;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventRepository eventRepository;

    SimpleDateFormat recordFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @PostMapping("/newCost")
    public Event addNewCost(@RequestBody Event event){
        String random = RandomId.getUniqueKey();
        event.setId(random);
        event.setDate(recordFormatter.format(new Date()));
        eventRepository.save(event);
        return event;
    }

    @PostMapping("/oldCost")
    public Event addOldCost(@RequestBody Event event){
        String random = RandomId.getUniqueKey();
        event.setId(random);
        event.setDate(recordFormatter.format(new Date()));
        eventRepository.save(event);
        return event;
    }

    @PostMapping("/changeStatus")
    public Event changeRepairStatus(@RequestBody Event event){
        String random = RandomId.getUniqueKey();
        event.setId(random);
        event.setDate(recordFormatter.format(new Date()));
        eventRepository.save(event);
        return event;
    }

    @PostMapping("/getAll")
    public List<Event> getAllEvents(@RequestParam(value = "temp", required = false) String temp){
        return eventRepository.findAll();
    }

    @PostMapping("/addNewDevice")
    public Event addNewDevice(@RequestBody Event event){
        String random = RandomId.getUniqueKey();
        event.setId(random);
        event.setDate(recordFormatter.format(new Date()));
        eventRepository.save(event);
        return event;
    }
}
