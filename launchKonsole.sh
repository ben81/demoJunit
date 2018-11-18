ls -d */ | awk '{print }' | xargs -n1 sh -c 'konsole --new-tab --workdir $PWD/$0  '
