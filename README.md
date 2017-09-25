Title : Student Safety Engagement Program

Features:

Web based

    -- Intellij with Spring MVC

Authentication

    -- Different views for different users
    --** Verification done by: Administrator | Supervisor**
    --** Cannot** make an account by yourself
        New users must go through application/interview
    -- ***For encryption, TLS v1.2 should be used - SSL is broken and can too easily be hacked.***

Scheduling

    -- Weeklong schedule, could possibly be extended and see dates later than current
    -- Click a time slot, can enter a volunteer
    -- Each time slot ~1 hour
    -- Time can start any time and end any time
        Generally from 8am – 4pm
    -- Save/Edit/Export schedule
    -- Can be modified only by: Administrator | Supervisor | Team Lead
    -- Can be read by: Regular members

    Types of Shifts

    BURNABY LOCATION

    -- Information and Lost&Found Kiosk
    -- Speed Watch/Moving Traffic
    -- Community Presence
    -- Safety Screen
    -- Theft Prevention
    -- Auto Theft Prevention
    -- Bike Presence
    -- Special Events (peaceful protests, campus ceremonies, etc)
    -- Smoking Checks
    -- Pedestrian Safety


SURREY LOCATION

    -- Community Presence
    -- Theft Prevention
    -- Special Events (peaceful protests, campus ceremonies, etc)
    -- Pedestrian Safety


VANCOUVER LOCATION

    -- Community Presence
    -- Theft Prevention
    -- Special Events (peaceful protests, campus ceremonies, etc)
    -- Pedestrian Safety

Statistics

    -- Record certain categories (will be provided by the slideshow presentation by customer)
    -- Somewhat like an excel graph
    -- Save/Edit/Export statistics
    -- Can be modified only by: Administrator | Supervisor (maybe Team Lead? Not sure)
    --** Cannot** be read by: Regular members (need to double check this)

Payroll

    -- Record wages per person
    -- Volunteers cannot have a wage
    -- Save/Edit/Export payroll
    -- Can be modified only by: Administrator | Supervisor
    --** Cannot** be read by: Team Lead | Regular members
    -- *** one cannot see another's wage ***

Information Booth

    -- Consists of contact information (e-mail/phone number/etc.)

Lost & Found

    -- Consists of location on which campus to find
    -- Might be able to shove this in the information booth?

Potential Features:

Auto e-mail of schedules to users

    -- Must get some automated e-mail system to send to all members

Confirmation of shifts

    -- Whenever a Member is logged in and has a shift coming up sometime soon (one day?), there should be a confirmation of whether they can attend.
        Maybe a page that shows your "confirmed" and "unconfirmed" shifts?

Certificates after x hours

    --** Authenticated by: Administrator | Supervisor**
    -- Not too sure about this one, need more info

Recommendation of which member to take as to balance hours for every member (going to have to do this in later iterations)

Bootstrap (can be considered once we have the actual website working on computer)

    -- Mobile friendliness

Log

    -- Maybe keep one of these for rollbacks

Users:

Administrator (Highest level)

    -- Deals with Scheduling, Statistics, Payroll, website itself, etc. May add a function for modification of users.
    --** ***Can basically do anything*****

Supervisor (Second highest)

    -- Can read and modify: mostly everything
    -- Like Administrator , but cannot change values of the Administrator

Team Lead (Third highest)

    -- Assigns Members to time slots
    -- Can modify*: Schedules | Statistics
        *Can modify ONLY their own respective team members' profile

Members (Paid/Volunteer) (Lowest level)

    -- Receives e-mail of schedules
    -- Cannot modify profile
    -- Schedules are private (another Member cannot see another Member's schedule, but higher-level users can)
    -- Can read schedule; cannot modify\* schedule
        *Can state availability
    -- Can confirm shifts

Profile

    -- Name, e-mail, phone number, student number, profile picture
    -- Should state preferred campus (colored per campus on member list?)
    -- Is set up by: Administrator | Supervisor
    -- Can be modified by: Administrator | Supervisor | Team Lead
    --** Cannot be modified by: Regular ***members*

User Stories

Administrator

    Enters website
    Logs in via either; SFU authentication, general authentication, or API
    Is immediately shown a dashboard that contains all important information regarding upcoming important events/due dates ( Payroll , Scheduling , etc)
        All web pages should also include a retractable sidebar, which contains links to main pages ( Statistics , Payroll , etc.)

    Case 1: Administrator visits Scheduling via sidebar
        By clicking Scheduling , Administrator should see a sidebar and main content
            Content consists of a dynamic calendar
                Calendar can have modal views (by clicking a certain day/time slot, Administrator can add in members
            Administrator can save/edit/export calendar as needed
                if Save: database updates the changed values from website and successfully is reflected in database
                if Edit: calendar can be modified
                if Export: should export into an excel(?) file.

    Case 2: Administrator visits Statistics via sidebar
        By clicking Statistics , Administrator should see a sidebar and main content
            Content consists of a few tables that consist of TBA values/categories that display a real-time graph. These values can be modified by either Administrator, Supervisor, AND respective Team Lead
            Administrator, Supervisor, Team Lead can save/edit/export tables & graph as needed
                if Save : database updates the changed values from website and successfully is reflected in database
                if Edit: values are modified
                if Export: should export into an excel(?) file.

    Case 3: Administrator visits Payroll via sidebar
        By clicking Payroll, Administrator should see a sidebar and main content
            Content consists of a table that contains all wage related items per person. These values can be modified by only Administrator and Supervisor.
            Administrator and Supervisor can save/edit/export tables as needed
                if Save : database updates the changed values from website and successfully is reflected in database
                if Edit: values are modified
                if Export: should export into an excel(?) file.

    Case 4: Administrator visits Profiles via sidebar
        By clicking Profiles, Administrator should see a sidebar and main content
            Content consists of a long table that contains all users
                Members can be sorted depending on user level, alphabetical, or campuses
                    Potential Feature: add a search filter
            If Administrator clicks on a Member's profile
                Administrator sees
                    Picture
                    Name
                    Preferred campus(es)
                    E-mail
                    Phone Number
                    Modify buttons (save/edit/delete/export(?))
                        if Save : database updates the changed values from website and successfully is reflected in database
                        if Edit: values are modified
                        if Delete: values are deleted from website/database and is successfully reflected in database
            Administrator should be able to Add Members OR Delete Members
                *Somewhere in the main content, there should be a button that enables the Administrator to fill out a form that should add said new user into the database and website.

    Case 5: Administrator visits Information & Contact Us via sidebar
        Sidebar and main content should be visible (do it like this don't forget)
            Content consists of pictures of specific people to meet?
                Administrator, Supervisor, Team Lead?
