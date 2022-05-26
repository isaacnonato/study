# Peer-to-peer file distribution (P2P)

Unlike most other web applications, P2P file distribution does not rely on a centralized server. Instead, pair of hosts, caled peers, communicate directly with each other, the peers aren't owned by service providers, but by the users themselves.

In a P2P file distribution, each peer redistribute any portion of the file it has received to the others peers. The most popular P2P file distribution protocol is BitTorrent, originally developed by Bram Cohen.

## BitTorrent

In BitTorrent, the collection of peers participating in the distribution ofa certain file is called a torrent. Peers in a torrent download equal-size chunks of file from one another, with a typical chunk size of 256kbytes. When a peer first joins a torrent, it has no chunks, and it accumulate chunks over time as it receives them from other peers. The newly connected peer may also upload its chunks to other peers. Once a peer acquired the entire file, it may leave the torrent or remain in the torrent to keep uploading chunks to other peers (called seeding). 

### How BitTorrent works 

Each torrent has an infrastructure node called a tracker. The role of the tracker is to keep the IP addresses of other seeds and peers in the torrent.

Let's say a peer P joins a torrent. The tracker randomly selects a number N of peers from the set and sends the IP addresses of these N peers to P. 
Then, P attempts to establish multiple TCP connections with the with all the N peers of the given list. As time evolves, some peers may leave and other peers may join (attempt to establish TCP connections with P).

At any given time, each peer will have a subset of chunks from the file, with different peers having different subsets. Periodically, P will ask for any of its neighboring peers (over the TCP connections) for their list of chunks, if P has L different neighbors, it will receive L different lists.
With this knowledge, P will issue requests for chunks it does not currenly have.

With this information, P will have two decisions to make:

* Which chunks should be requested first from its neighbors?

* To which of its neighbors shoud it send requested chunks?

The BitTorrent protocol uses a technique called __rarest first__. The idea is to determine, from among the chunks P does not have, the chunks that are rarest among its neighbors (that is, the chunks with less repetitions among them). In this way, the rarest chunks get more quickly redistributed, aiming to equalize the numbers of each chunk in the torrent.

To determine which requests P responds to, BitTorrent uses a trading algorithm.
The algorithm works so P gives priority to its neighbors that are supplying it data at the _highest rate_. Specifially, for each of its neighbors, P measures the rates at which it receives bits every 10 seconds.

# To read

[BitTorrent Specification](https://wiki.theory.org/BitTorrentSpecification)
