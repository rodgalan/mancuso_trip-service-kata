package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

  @Mock
  private TripDAO tripDAO;
  private TripService tripService;

  @Before
  public void setup() {
    tripService = new TripService(tripDAO);
  }

  @Test(expected = UserNotLoggedInException.class)
  public void when_user_not_logged_then_fails() {
    tripService.getTripsByUser(null, null);
  }

  @Test
  public void when_users_are_not_friends_then_no_trips_are_returned() {
    User loggedUser = new User.Builder().build();
    User requestedUser = new User.Builder().build();

    List<Trip> trips = tripService.getTripsByUser(loggedUser, requestedUser);

    assertThat(trips).isEmpty();
  }

  @Test
  public void when_users_are_friends_then_trips_are_returned() {
    Trip trip = new Trip();
    User loggedUser = new User.Builder().build();
    User requestedUser = new User.Builder().withFriend(loggedUser).build();

    when(tripDAO.findTrips(eq(requestedUser))).thenReturn(Arrays.asList(trip));
    List<Trip> trips = tripService.getTripsByUser(loggedUser, requestedUser);

    assertThat(trips).containsExactly(trip);
  }

}

	

