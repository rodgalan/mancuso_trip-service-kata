package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

  @Test
  public void is_not_user_friend(){
    User user = new User.Builder().build();
    User other_user = new User.Builder().build();
    boolean isFriend = user.isFriendOf(other_user);
    assertThat(isFriend).isFalse();
  }

  @Test
  public void is_user_friend(){
    User user = new User.Builder().build();
    User other_user = new User.Builder().withFriend(user).build();
    boolean isFriend = user.isFriendOf(other_user);
    assertThat(isFriend).isFalse();
  }

}
