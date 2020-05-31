# CreditVoidKill
Allows you to credit killer of player knocked into void

*To download, go to https://github.com/prosteDeniGC/CreditVoidKill/releases*

## How does it work?

* When player gets kicked into the void, his death will be announced, and killer, the last person who punched him, will get his credit.
* It does have tab autocomplete, although this feature won't be very useful as there's only one command and one argument. Oh well, at least my inner aesthet is satisfied
* If scoreboard objective **playerKills** exists, it adds 1 for the killer

## Commands

* /creditvoidkill reload

reloads the config

## Permissions

* creditvoidkill.admin

will authorise player to reload the config

## Config

* Timer: 8

amount of seconds from last punch to player to his death in the void, 8 works perfectly fine, but make sure to set it higher if player falls from higher position

* KillMessage: '&4%player1% &3has been knocked into void by &6%player2%'
  
message that gets displayed when player gets killed by getting kicked into void. Does support color codes. **%player1%** will be replaced with name of killed player while **%player2%** will get replaced with his killer
