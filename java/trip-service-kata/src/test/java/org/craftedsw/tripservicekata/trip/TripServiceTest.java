package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TripServiceTest {

  @Test(expected = UserNotLoggedInException.class)
  public void when_user_not_logged_then_fails() {
    TripServiceTestable tripService = new TripServiceTestable(null);
    tripService.getTripsByUser(null);
  }

  @Test
  public void when_users_are_not_friends_then_no_trips_are_returned() {
    User loggedUser = new UserBuilder().build();
    User requestedUser = new UserBuilder().build();

    TripServiceTestable tripService = new TripServiceTestable(loggedUser);
    List<Trip> trips = tripService.getTripsByUser(requestedUser);

    assertThat(trips).isEmpty();
  }

  @Test
  public void when_users_are_friends_then_trips_are_returned() {
    Trip trip = new Trip();
    User loggedUser = new UserBuilder().build();
    User requestedUser = new UserBuilder().withFriend(loggedUser).withTrip(trip).build();

    TripServiceTestable tripService = new TripServiceTestable(loggedUser);
    List<Trip> trips = tripService.getTripsByUser(requestedUser);

    assertThat(trips).containsExactly(trip);
  }

  static class UserBuilder{
    private User user;

    UserBuilder() {
      this.user = new User();
    }

    User build(){
      return user;
    }

    UserBuilder withFriend(User friend){
      user.addFriend(friend);
      return this;
    }

    UserBuilder withTrip(Trip trip){
      user.addTrip(trip);
      return this;
    }

  }
}

class TripServiceTestable extends TripService {
  private final User user;

  TripServiceTestable(User user) {
    this.user = user;
  }

  @Override
  protected User getLoggedUser() {
    return user;
  }

  @Override
  protected List<Trip> getUserTrips(User user) {
    return user.trips();
  }
}

	

