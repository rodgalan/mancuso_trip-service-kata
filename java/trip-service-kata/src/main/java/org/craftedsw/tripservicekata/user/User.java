package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;

public class User {

  private List<Trip> trips = new ArrayList<Trip>();
  private List<User> friends = new ArrayList<User>();

  public List<User> getFriends() {
    return friends;
  }

  public void addFriend(User user) {
    friends.add(user);
  }

  public void addTrip(Trip trip) {
    trips.add(trip);
  }

  public List<Trip> trips() {
    return trips;
  }

  public boolean isFriendOf(User other_user) {
    return friends.stream().anyMatch(friend -> friend.equals(other_user));
  }

  public static class Builder {
    private List<Trip> trips;
    private List<User> friends;

    public Builder() {
      friends = new ArrayList<>();
      trips = new ArrayList<>();
    }

    public User build() {
      User user = new User();
      trips.forEach(trip -> user.addTrip(trip));
      friends.forEach(friend -> user.addFriend(friend));
      return user;
    }

    public Builder withFriend(User friend) {
      friends.add(friend);
      return this;
    }

    public Builder withTrip(Trip trip) {
      trips.add(trip);
      return this;
    }
  }

}
