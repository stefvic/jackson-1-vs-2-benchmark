package jmh.jackson;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

/**
 * create one {@link User} with X number of friends
 *
 * @author erabii
 *
 */
@State(Scope.Thread)
public class InputSer {

    public List<User> users;

    private String[] letters = { "q", "a", "z", "w", "s", "x",
            "e", "d", "c", "r", "f", "v", "t", "g", "b",
            "y", "h", "n", "u", "j", "m", "i", "k", "o", "l", "p" };

    @Param(value = { "100", "1000", "10000" })
    int next;

    @TearDown(Level.Iteration)
    public void tearDown() {
        if (next == 100) {
            User previous = users.remove(0);
            assert previous.getFriends().size() == 100;
            assert previous.getAge() == 100;
            assert previous.getId().length() == 3;
            previous.getFriends().removeIf(x -> true);
            users = null;
        }

        if (next == 1_000) {
            User previous = users.remove(0);
            assert previous.getFriends().size() == 1000;
            assert previous.getAge() == 1000;
            assert previous.getId().length() == 4;
            previous.getFriends().removeIf(x -> true);
            users = null;
        }

        if (next == 10_000) {
            User previous = users.remove(0);
            assert previous.getFriends().size() == 10_000;
            assert previous.getAge() == 10_000;
            assert previous.getId().length() == 5;
            previous.getFriends().removeIf(x -> true);
            users = null;
        }
    }

    @Setup(Level.Iteration)
    public void setUp() {

        users = new ArrayList<>(1);
        List<Friend> friends = new ArrayList<>(next);
        for (int i = 0; i < next; ++i) {

            StringJoiner sj = new StringJoiner("");
            IntStream.rangeClosed(0, next)
                    .mapToObj(x -> letters[x % letters.length])
                    .forEach(sj::add);

            friends.add(new Friend("" + next, sj.toString()));
        }

        users.add(new User("" + next, next, next + 0.1D, next + 0.1D, next * next, friends));

    }
}
