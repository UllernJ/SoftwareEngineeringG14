package com.example.softwareg14.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourTest {

//    @Test
//    void tourShouldNotAllowSameUserTwice() {
//        Tour tour = new Tour();
//        tour.setMaxCapacity(35);
//        User user = new User();
//        user.setId(1);
//        user.setName("Test");
//        user.setUsername("test");
//        tour.addAttendant(user);
//        assertThrows(RuntimeException.class, () -> tour.addAttendant(user));
//        assertEquals(1, tour.getUsersAttending().size());
//    }
//    @Test
//    public void tourShouldNotAllowMoreThanMaxCapacity() {
//        Tour tour = new Tour();
//        User user = new User();
//        user.setId(1);
//        user.setName("Test1");
//        user.setUsername("test1");
//        tour.setMaxCapacity(1);
//        tour.addAttendant(user);
//        User user2 = new User();
//        user2.setId(2);
//        user2.setName("Test2");
//        user2.setUsername("test2");
//        assertThrows(RuntimeException.class, () -> tour.addAttendant(user2));
//        assertEquals(1, tour.getUsersAttending().size());
//    }
}