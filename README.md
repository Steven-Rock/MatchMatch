## Synopsis

MatchMatch is a card game that lays out 4x4 grid of cards face down. The object is to turn over 2 cards at a timee showing the images on the card underside. Every image has another matching card. If the 2 cards match, they will remain face up. If they do not match they will turn back face down. You must match all the cards in the shortest number of turns.

<p align="center">
  <img src="https://github.com/Steven-Rock/MatchMatch/blob/master/docs/images/start.PNG" height="550"/>
  <img src="https://github.com/Steven-Rock/MatchMatch/blob/master/docs/images/matched.PNG" height="550"/>
</p>


## Motivation

Full working REST Android application sample showcasing my work, and testbed to add/try new features like frameworks, etc.

## Installation

Download from github and open/run with Android Studio emulator or install to physical device

## Frameworks/APIs used

Picasso - for downloading images and caching.

Flickr - search the latest top 100 images uploaded to flickr. These are cached and shown to the user in increments of 8.

Rotate3DAnimation - I added z-axis card rotation so it looks like the card is being turned over when clicked

## Contributors

Steven Rock - lead developer
https://www.linkedin.com/in/steven-rock-0823294/

Send email to stevenrock@gmail.com if you would like to contribute

## TODO

- [ ] Fix images, are shown in mirror image (flipped) on the backside of cards, rotate before adding
- [ ] Store top 10 high scores, add person's initials when they get a high score
- [ ] Add menu item to see high scores
- [ ] Pagination on 100 at a time to get new flickr latest images
- [ ] Long press show image full screen


## License

MIT License

Copyright (c) [2017] Steven William Rock, SWR Technologies, LLC

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.