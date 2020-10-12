# Tip Calculator 

## Danielle Tang

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip.

Time spent: **10** hours spent in total

## Functionality 

The following **required** functionality is completed:

* [X] User can enter in a bill amount (total amount to tip on)
* [X] User can enter a tip percentage (what % the user wants to tip).
* [X] The tip and total amount are updated immediately when any of the inputs changes.
* [X] The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* [X] Custom colors palette selected
* [X] Emoji indicators for tip amount
* [X] Option to calculate split bill payments (only even split implemented)

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/danielletang/CS194A-assignment1/blob/main/Tippy%20Walkthrough.gif' title='Tippy Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [GIPHY](http://www.giphy.com).

## Notes

The video walkthroughs from Rahul were super clear and helpful. I realized while doing extensions
that I was way outside the scope of what I knew, so I spent a lot of time on StackOverflow and YouTube
to learn how to use Fragments and newInstance. I ended up not completing the functionality of two of
my bill splitting options because I didn't know how to dynamically update a Fragment based on user
input for the number of people splitting the bill. I did do the equally split option, though, and
that works just fine.