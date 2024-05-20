package com.example.dao;

import com.example.model.Activity;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {
    public List<Activity> getAllActivities() throws Exception {
        List<Activity> activities = new ArrayList<>();
        try (Connection con = DBUtil.getConnection()) {
            String query = "SELECT activity_id, user_id, activity_type, date, start_time, duration_minutes FROM Activities";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Activity activity = new Activity();
                        activity.setActivityId(rs.getInt("activity_id"));
                        activity.setUserId(rs.getInt("user_id"));
                        activity.setActivityType(rs.getString("activity_type"));
                        activity.setDate(rs.getDate("date"));
                        activity.setStartTime(rs.getTime("start_time"));
                        activity.setDurationMinutes(rs.getInt("duration_minutes"));
                        activities.add(activity);
                    }
                }
            }
        }
        return activities;
    }

    public Activity getActivityById(int activityId) throws Exception {
        Activity activity = null;
        try (Connection con = DBUtil.getConnection()) {
            String query = "SELECT activity_id, user_id, activity_type, date, start_time, duration_minutes FROM Activities WHERE activity_id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, activityId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        activity = new Activity();
                        activity.setActivityId(rs.getInt("activity_id"));
                        activity.setUserId(rs.getInt("user_id"));
                        activity.setActivityType(rs.getString("activity_type"));
                        activity.setDate(rs.getDate("date"));
                        activity.setStartTime(rs.getTime("start_time"));
                        activity.setDurationMinutes(rs.getInt("duration_minutes"));
                    }
                }
            }
        }
        return activity;
    }

    public void deleteActivity(int activityId) throws Exception {
        try (Connection con = DBUtil.getConnection()) {
            String query = "DELETE FROM Activities WHERE activity_id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, activityId);
                pst.executeUpdate();
            }
        }
    }
    public int getUserIdByUsername(String username) throws Exception {
        int userId = -1;
        try (Connection con = DBUtil.getConnection()) {
            String query = "SELECT user_id FROM Users WHERE username = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, username);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt("user_id");
                    }
                }
            }
        }
        return userId;
    }
    public void addActivity(Activity activity) throws Exception {
        try (Connection con = DBUtil.getConnection()) {
            String query = "INSERT INTO Activities (user_id, activity_type, date, start_time, duration_minutes) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, activity.getUserId());
                pst.setString(2, activity.getActivityType());
                pst.setDate(3, activity.getDate());
                pst.setTime(4, activity.getStartTime());
                pst.setInt(5, activity.getDurationMinutes());
                pst.executeUpdate();
            }
        }
    }
    public List<Activity> searchActivities(String username, String activityType, String date) throws Exception {
        List<Activity> activities = new ArrayList<>();
        try (Connection con = DBUtil.getConnection()) {
            StringBuilder query = new StringBuilder(
                    "SELECT activity_id, user_id, activity_type, date, start_time, duration_minutes " +
                            "FROM Activities WHERE user_id = (SELECT user_id FROM Users WHERE username = ?) ");
            if (activityType != null && !activityType.isEmpty()) {
                query.append("AND activity_type = ? ");
            }
            if (date != null && !date.isEmpty()) {
                query.append("AND date = ? ");
            }

            PreparedStatement pst = con.prepareStatement(query.toString());
            pst.setString(1, username);
            int index = 2;
            if (activityType != null && !activityType.isEmpty()) {
                pst.setString(index++, activityType);
            }
            if (date != null && !date.isEmpty()) {
                pst.setString(index, date);
            }

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Activity activity = new Activity();
                    activity.setActivityId(rs.getInt("activity_id"));
                    activity.setUserId(rs.getInt("user_id"));
                    activity.setActivityType(rs.getString("activity_type"));
                    activity.setDate(rs.getDate("date"));
                    activity.setStartTime(rs.getTime("start_time"));
                    activity.setDurationMinutes(rs.getInt("duration_minutes"));
                    activities.add(activity);
                }
            }
        }
        return activities;
    }
    public int getTotalDuration(String username, String startDate, String endDate) throws Exception {
        int totalDuration = 0;
        try (Connection con = DBUtil.getConnection()) {
            String query = "SELECT SUM(duration_minutes) AS total_duration FROM Activities WHERE user_id = (SELECT user_id FROM Users WHERE username = ?) AND date BETWEEN ? AND ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, username);
                pst.setString(2, startDate);
                pst.setString(3, endDate);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        totalDuration = rs.getInt("total_duration");
                    }
                }
            }
        }
        return totalDuration;
    }
}
