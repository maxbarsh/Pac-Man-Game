# Pac-Man-Game
A recreation of the classic arcade game, Pac-Man, written in Java.  This version of Pac-Man contains almost all of the same elements that the original game does, as well as an additional custom maze.  At the beginning of each level, Ghosts travel randomly around the maze.  After a set time, the Ghosts become "smart" and begin to converge on Pac-Man's location. As the levels go up, the Ghosts become smart more quickly.  Watch out!!
 
The simple GUI allows for a user to enter a three character ID so that their high score can be tracked at the end of each game. The threaded application runs at 60 fps and calls the tick() method once per frame. During each call to tick(), the position of Pac-Man is fed to the Ghosts and they calculate their own path to him. The path a ghost takes is not necessarily always the shortest one in an effort to make the game last a little longer. 
 
 Use the arrow keys or WASD to control Pac-Man.

