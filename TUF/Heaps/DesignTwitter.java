import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DesignTwitter {

    private static int timestamp = 0;

    private final Map<Integer, Set<Integer>> followMap;
    private final Map<Integer, TweetNode> tweetMap;

    private static class TweetNode {
        
        int tweetId;
        int time;
        TweetNode next;

        TweetNode(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    public DesignTwitter() {

        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {

        TweetNode node = new TweetNode(tweetId, timestamp++);
        node.next = tweetMap.get(userId);
        tweetMap.put(userId, node);
    }

    public List<Integer> getNewsFeed(int userId) {

        List<Integer> feed = new ArrayList<>();

        PriorityQueue<TweetNode> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.time, a.time)
        );

        if (tweetMap.containsKey(userId)) {
            maxHeap.add(tweetMap.get(userId));
        }

        Set<Integer> followees = followMap.getOrDefault(userId, new HashSet<>());
        for (int followee : followees) {
            TweetNode head = tweetMap.get(followee);
            if (head != null) {
                maxHeap.add(head);
            }
        }

        while (!maxHeap.isEmpty() && feed.size() < 10) {
            TweetNode current = maxHeap.poll();
            feed.add(current.tweetId);

            if (current.next != null) {
                maxHeap.add(current.next);
            }
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {

        if (followerId == followeeId) return;

        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (!followMap.containsKey(followerId)) return;

        followMap.get(followerId).remove(followeeId);
    }

    public static void main(String[] args) {

        DesignTwitter obj = new DesignTwitter();

        obj.postTweet(1, 101);
        obj.postTweet(1, 102);
        obj.postTweet(1, 103);
        obj.postTweet(1, 104);

        obj.postTweet(2, 201);
        obj.postTweet(2, 202);
        obj.postTweet(2, 203);
        obj.postTweet(2, 204);

        obj.postTweet(3, 301);
        obj.postTweet(3, 302);
        obj.postTweet(3, 303);
        obj.postTweet(3, 304);

        obj.follow(1, 2);
        obj.follow(1, 3);
        System.out.println(obj.getNewsFeed(1)); // [304, 303, 302, 301, 204, 203, 202, 201, 104, 103]

        obj.unfollow(1, 3);
        System.out.println(obj.getNewsFeed(1)); // [204, 203, 202, 201, 104, 103, 102, 101]

    }

}
