#!/bin/sh
# gvSIG.sh

###########################################
# Variables depending on the installation #
###########################################


# gvSIG installation folder
GVSIG_HOME=`dirname "$0"`

# Go into the gvSIG installation folder, just in case
cd "${GVSIG_HOME}"

# Java home
export JAVA_HOME="../jre/1.6.0_20"


###################################################################
# Variables not depending (at least directly) on the installation #
###################################################################

# gvSIG native libraries location 
GVSIG_NATIVE_LIBS=${GVSIG_HOME}/native:${HOME}/.depman/lib

# Proj4 data files
export PROJ_LIB="${GVSIG_HOME}/gvSIG/extensiones/org.gvsig.crs.extension/data"

# GDAL data files
export GDAL_DATA="${GVSIG_HOME}/data/gdal"

# Native libraries path
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GVSIG_NATIVE_LIBS"



# Load gvSIG Andami jars and dependencies for the classpath 
for i in ./lib/*.jar ; do
  LIBRARIES="$LIBRARIES":"$i"
done
for i in ./lib/*.zip ; do
  LIBRARIES="$LIBRARIES":"$i"
done
LIBRARIES="$LIBRARIES":andami.jar

# echo Initial libraries found: ${LIBRARIES}

# gvSIG Andami launcher
GVSIG_LAUNCHER=com.iver.andami.Launcher

# gvSIG initial classpath
GVSIG_CLASSPATH=$LIBRARIES

########################
# Memory configuration #
########################

# Initial gvSIG memory (M=Megabytes, G=Gigabytes)
GVSIG_INITIAL_MEM=256M
# Maximum gvSIG memory (M=Megabytes, G=Gigabytes)
GVSIG_MAX_MEM=1024M
# Maximum permanent memory size: needed to load classes and statics
GVSIG_MAX_PERM_SIZE=96M

################
# Launch gvSIG #
################

# Temporary fix for number locale related formatting error with proj4.
export LC_NUMERIC=C

# For Java parameters documentation and more parameters look at:
# http://download.oracle.com/javase/6/docs/technotes/tools/windows/java.html
# http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html

echo Launching gvSIG: ${JAVA_HOME}/bin/java \
	-Djava.library.path=/usr/lib:"${GVSIG_NATIVE_LIBS}" \
	-cp $GVSIG_CLASSPATH \
	-DgvSIG.confDir="$GVSIG_HOME/../cfg" \
	-Xms${GVSIG_INITIAL_MEM} \
	-Xmx${GVSIG_MAX_MEM} \
	-XX:MaxPermSize=${GVSIG_MAX_PERM_SIZE} \
	$GVSIG_LAUNCHER gvSIG gvSIG/extensiones "$@"

${JAVA_HOME}/bin/java \
	-Djava.library.path=/usr/lib:"${GVSIG_NATIVE_LIBS}" \
	-cp "$GVSIG_CLASSPATH" \
	-DgvSIG.confDir="${GVSIG_HOME}/../cfg" \
	-Xms${GVSIG_INITIAL_MEM} \
	-Xmx${GVSIG_MAX_MEM} \
	-XX:MaxPermSize=${GVSIG_MAX_PERM_SIZE} \
	"${GVSIG_LAUNCHER}" gvSIG gvSIG/extensiones "$@"
