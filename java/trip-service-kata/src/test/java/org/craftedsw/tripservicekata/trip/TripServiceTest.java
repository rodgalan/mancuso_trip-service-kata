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
    User loggedUser = new User();
    User requestedUser = new User();

    TripServiceTestable tripService = new TripServiceTestable(loggedUser);
    List<Trip> trips = tripService.getTripsByUser(requestedUser);

    assertThat(trips).isEmpty();

  }
}



   class TripServiceTestable extends TripService {
    private final User user;

    public TripServiceTestable(User user) {
      this.user = user;
    }

    @Override
    protected User getLoggedUser() {
      return user;
    }

  }

	

