# Selenium Grid

Hub Url:
http://<hub-machine-ip>:4444/wd/hub

## Docker command for hub:

docker pull selenium/hub
docker run -d -p 4444:4444 –name selenium-hub selenium/hub

## Command to grid console

http://<docker host ip>:4444/grid/console

## Docker command for chrome node:

docker run -d –link selenium-hub:hub selenium/node-chrome

## Docker command for firefox node:

docker run -d –link selenium-hub:hub selenium/node-firefox
