@echo off
cd ..
git submodule update --remote lib/nwawsoft-java-util
git add lib/nwawsoft-java-util
git commit -m "Updated library nwawsoft-java-util to latest commit."
git push
