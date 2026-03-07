# Design Twitter: Intuition and Solution

## Problem in Simple Words
We need to build a mini Twitter with 4 operations:
- `postTweet(userId, tweetId)`
- `getNewsFeed(userId)`
- `follow(followerId, followeeId)`
- `unfollow(followerId, followeeId)`

`getNewsFeed(userId)` must return up to 10 most recent tweet IDs from:
- the user itself
- users they follow

The hard part is getting the latest tweets quickly without sorting everything from scratch every time.

## Core Intuition
Each user has a tweet stream sorted by time (newest to oldest).
The news feed is a merge of multiple already-sorted streams.

So the key question is:
How do we repeatedly pick the most recent tweet among many stream heads?

Answer: use a max-heap.

## Data Structures Used in `DesignTwitter.java`
- `followMap: Map<Integer, Set<Integer>>`
  - `followerId -> set of followeeIds`
- `tweetMap: Map<Integer, TweetNode>`
  - `userId -> head of that user's tweet linked list`
- `TweetNode`
  - fields: `tweetId`, `time`, `next`
- `timestamp` (global increasing counter)
  - guarantees ordering by recency

## Why We Used Heap
In `getNewsFeed`, each relevant user contributes one sorted linked list (newest at head).
If there are `K` such lists, we only need to compare their current heads, not all tweets.

A max-heap lets us:
1. get the most recent available tweet in `O(log K)`
2. insert the next tweet from the same user list in `O(log K)`

This is a K-way merge pattern and is better than collecting all tweets and sorting on every feed request.

## Method-by-Method Explanation
- `postTweet(userId, tweetId)`
  - create `TweetNode(tweetId, timestamp++)`
  - insert at head of `tweetMap[userId]`
  - time: `O(1)`

- `follow(followerId, followeeId)`
  - add followee to follower's set
  - ignore self-follow (`followerId == followeeId`)
  - time: `O(1)` average

- `unfollow(followerId, followeeId)`
  - remove followee if present
  - safe no-op if missing
  - time: `O(1)` average

- `getNewsFeed(userId)`
  - push own head tweet (if present) and each followee head tweet (if present) into max-heap by `time`
  - repeatedly pop latest tweet and add `tweetId` to result
  - push popped node's `next` tweet (if exists)
  - stop after 10 tweets or heap becomes empty
  - time: about `O((K + 10) log K)`, where `K` is the number of streams inserted into heap

## Worked Example with User IDs 1, 2, 3
Tweet IDs pattern:
- User 1: `101`, `102`, `103`, `104`
- User 2: `201`, `202`, `203`, `204`
- User 3: `301`, `302`, `303`, `304`

Suppose operations happen in this order:
1. `postTweet(1, 101)`
2. `postTweet(1, 102)`
3. `postTweet(1, 103)`
4. `postTweet(1, 104)`
5. `postTweet(2, 201)`
6. `postTweet(2, 202)`
7. `postTweet(2, 203)`
8. `postTweet(2, 204)`
9. `postTweet(3, 301)`
10. `postTweet(3, 302)`
11. `postTweet(3, 303)`
12. `postTweet(3, 304)`
13. `follow(1, 2)`
14. `follow(1, 3)`

Now `getNewsFeed(1)` returns:
- `[304, 303, 302, 301, 204, 203, 202, 201, 104, 103]`

If user 1 unfollows user 3:
15. `unfollow(1, 3)`

Then `getNewsFeed(1)` returns:
- `[204, 203, 202, 201, 104, 103, 102, 101]`

## Edge Cases
- self-follow is ignored
- user with no tweets and no followees gets `[]`
- unfollow non-followed user is a safe no-op
- duplicate follow calls are naturally handled by `Set`

## Notes About Current Code
- `timestamp` is `static`, so all `DesignTwitter` objects share one counter.
- `timestamp` is `int`, so theoretical overflow is possible only at very large tweet counts.

## Code Demo Mapping
- This markdown and `main` in `DesignTwitter.java` both use IDs up to `104`, `204`, and `304`.

## Summary
This design is efficient because:
- posting is constant time
- follow/unfollow are constant time average
- feed retrieval uses heap-based merge instead of full re-sorting
