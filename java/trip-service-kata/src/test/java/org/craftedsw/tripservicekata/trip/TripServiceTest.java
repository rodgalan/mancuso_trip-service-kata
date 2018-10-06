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
    User loggedUser = new User.Builder().build();
    User requestedUser = new User.Builder().build();

    TripServiceTestable tripService = new TripServiceTestable(loggedUser);
    List<Trip> trips = tripService.getTripsByUser(requestedUser);

    assertThat(trips).isEmpty();
  }

  @Test
  public void when_users_are_friends_then_trips_are_returned() {
    Trip trip = new Trip();
    User loggedUser = new User.Builder().build();
    User requestedUser = new User.Builder().withFriend(loggedUser).withTrip(trip).build();

    TripServiceTestable tripService = new TripServiceTestable(loggedUser);
    List<Trip> trips = tripService.getTripsByUser(requestedUser);

    assertThat(trips).containsExactly(trip);
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

	

