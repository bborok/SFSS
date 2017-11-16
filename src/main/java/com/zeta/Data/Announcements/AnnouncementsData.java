package com.zeta.Data.Announcements;

import com.zeta.Models.Announcement;

import java.util.List;

public interface AnnouncementsData {

    public boolean addAnnouncement(Announcement announcement);

    public Announcement showAnnouncements(int ID);

    public List<Announcement> showAllAnnouncements();
}