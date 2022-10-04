#!/bin/bash

# creator: mattie

prereq() {
    pkg update
    pkg install x11-repo
    pkg install tigervnc xfce4-session xfce4-goodies
    if [ -d $HOME/.vnc ]; then
        rm -rf $HOME/.vnc
    fi
    
    mkdir $HOME/.vnc
    VNC_HOME=$HOME/.vnc
    touch $VNC_HOME/xstartup
    echo '#!/bin/bash' > $VNC_HOME/xstartup
    echo '' > $VNC_HOME/xstartup
    echo 'xfce4-session &' > $VNC_HOME/xstartup
    chmod +x $VNC_HOME/xstartup
}

clean() {
    # kill all processes
    pids=`ps -e | awk '{ if(!($4=="bash"||$4=="CMD")) {print $1} }'`

    for p in $pids; do
        kill -9 $p
    done

    # delete all temporary files
    TMPPATH="$HOME/../usr/tmp"
    rm -rf $TMPPATH/* $TMPPATH/.*
}

start() {
    clean
    # start vnc server
    vncserver -listen tcp :1
    DISPLAY=:1 xhost +
}

if [ "$1" == "prereq" ]; then
    prereq
elif [ "$1" == "clean" ]; then
    clean
else
    start
fi
