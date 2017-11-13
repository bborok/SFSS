package com.zeta.Data.Announcements;

import com.zeta.Models.Announcements;

import java.util.List;

public interface AnnouncementsData {
    Boolean addAnnouncement(Announcements announcement); // required data to add an announcement
    Announcements getAnnouncements(int id); //get the id associated with the announcement
    List<Announcements> getAllAnnouncements(); //get all announcements

}
