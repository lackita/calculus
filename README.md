# Calculus Study Aid

I barely remember Calculus. I studied Math in college, but after not using it for ten years 
the skill has atrophied. The thing I want to ensure is that, not only do I relearn it, but
that I never find myself in this position again.

Spaced repetition is a technique to time review of a concept for when you're about to forget
it. This has been found to be the most efficient way to build and retain knowledge, and I'd
like to apply it to this subject. Supermemo has published SM-2 as usable by anybody as long 
as I include "Algorithm SM-2, (C) Copyright SuperMemo World, 1991. http://www.supermemo.com 
http://www.supermemo.eu", and it's a decent starting point.

Where this tool will diverge is in what's reviewed. Supermemo spends a lot of effort 
encouraging people to break down the knowledge they're trying to learn into bite size 
chunks, but in Mathematics the connections between concepts are as important as the facts 
themselves. Furthermore, I don't want memorize facts but learn the application of a concept
to actual problems. 

To solve these limitations, I have two major tactics. The first is generation of problems 
when a concept is to be reviewed instead of bringing up the same information each time. So,
for example, instead of always asking if \(f(x) = x^2\) is an even function, I'll generate 
an even exponent. The second is to be able to generate problems which bring multiple
concepts together.

It will be trickier to know when to bring problems which are combinations of concepts up 
for review. I'm still working on how to do this effectively, but my general idea is to 
layer a partially understood concept on top of already understood concepts. So let's say
I understand the exponent rule but am still struggling with the chain rule, then a 
problem generated from both the exponent rule and chain rule would be valid to bring up,
but something combining the chain rule and quotient rule may be beyond what I should
be reviewing at this point.

# Development

I'm using boot as the build tool for this project, mostly because that's what the Pedestal
tutorial I was following used. I'm using Pedestal because I know one of the developers on
the project and can bug him for help if I get in a pinch. Here's what I'm doing right now
to get things up and running:

```clojure
$ cd calculus
$ boot repl
> (defn restart []
    (require :reload 'calculus.server)
    (server/restart))
> (server/start-dev)
...do some coding...
> (restart)
...load specified url in web browser...
```

This probably isn't the most efficient way of interacting with these tools, it's just what
I've cobbled together from tutorials. I'll try to keep this up to date as I learn more 
about the toolchain.
