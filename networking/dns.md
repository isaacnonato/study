# What is DNS and how it works?

DNS is a distributed database that resolves URLs to their respective IP addresses.
Let's say you type `www.github.com` in your browser search tab, the following will happen:
1. Your browser will pass the hostname (that is, `www.github.com`) to the DNS client
2. The DNS client then sends the a query with the hostname to the DNS server.
3. The DNS server receives and resolves the IP address of the hostname
4. The server then sends a query with the IP address to the DNS client.
5. The browser then receives the IP address from DNS, and is ready to establish a connection with the received IP address.

Is important to note that the operating system also holds a DNS cache to reduce the DNS servers' traffic, so often the desired IP address is already cached.

To read: RFC 1034 and 1035

## DNS caching 

DNS servers can also cache queries to reduce the number of queries send across the internet. In a query chain, when a certain host receives a DNS reply, it can cache it to its local memory. Let's say a host `foo.ufabc.edu.br`queries `dns.ufabc.edu.br` for the hostname `github.com`, after a few hours, another host `bar.ufabc.edu.br` also queries `dns.ufabc.edu.br` for the same hostname. Because of DNS caching, the local DNS server will be able to immediately return the IP of `github.com`, to the second request without doing any additional queries.

## DNS records and messages

DNS servers store *resource records* (often abbreviated as RR), includig RRs that provide hostname-to-IP address mappings. Each DNS message carries one or more RRs. A resource record is a four-tuple with the following fields:

`(Name, Value, Type, TTL)`

`TTL` is the time to live of the record; it determines when a resource should be removed from the cache. The `Name` and `Value` fields depend on the `Type`. `Type` can have the values `A`, `NS`, `CNAME` or `MX`, those will be discussed below (in the below examples, the `TTL` field will be omitted): 

* If `Type=A`, then the `Name` field is a hostname and `Value` is the IP address for the hostname. So Type A provides the basic hostname-to-IP address mapping. Ex.: `(relay1.bar.foo.edu, 76.187.143.131, A)`

* If `Type=NS`, then `Name` is a domain (such as `github.com`) and `Value` is the hostname of an authoritative DNS server. This record is used to route DNS queries further along the query chain. Ex.: `(foo.com, dns.foo.com, NS)`

* If `Type=CNAME`, then `Value` is a canonical hostname for the alias hostname `Name`. This record can provide querying hosts the canonical name for a host-name. Ex.: `(foo.com, relay1.bar.foo.com, CNAME)`

* If `Type=MX`, `Value` is the canonical name of a mail server that has an alias hostname `Name`. Ex.: `(foo.com, mail.bar.foo.com, MX)`

If a DNS server is authoritative for a certain host, then it will contain a Type A record for the hostname (even it the server it's not authoritative, it may contain a Type A record on its cache). 

To read: RFC 2136 and 3007
