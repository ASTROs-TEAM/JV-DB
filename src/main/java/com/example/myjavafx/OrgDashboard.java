package com.example.myjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrgDashboard {

    @FXML
    private Label OrgDashboardName;

    @FXML
    private Label OrgEventCardName;
    @FXML
    private Label OrgEventCardVenue;
    @FXML
    private Label OrgEventCardNoofAttendees;
    @FXML
    private Label OrgEventCardTicketAvail;
    @FXML
    private Label OrgEventCardTicketFare;



    public void setName(String name){
        OrgDashboardName.setText(name.toUpperCase());
    }

    public void LoadEventsOrg(String OrgId) throws SQLException {

        DBConnection connect =  new DBConnection();
        Connection connectDb =connect.getConnection();

        String getEventsQuery = "select * from events where org_id='"+OrgId+"';";

        Statement statement = connectDb.createStatement();
        ResultSet queryresult= statement.executeQuery(getEventsQuery);
        while (queryresult.next()){
            OrgEventCardName.setText(queryresult.getString("event_name"));
            OrgEventCardVenue.setText(queryresult.getString("venue"));
            OrgEventCardNoofAttendees.setText(Integer.toString(queryresult.getInt("no_of_attendees")).concat(" Attending"));
            OrgEventCardTicketAvail.setText(Integer.toString(queryresult.getInt("tickets_remaining")).concat(" tickets available"));
            OrgEventCardTicketFare.setText(Integer.toString(queryresult.getInt("ticket_fare")).concat(" /ticket"));
        }


    }

}
