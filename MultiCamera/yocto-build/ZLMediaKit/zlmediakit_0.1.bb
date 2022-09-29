inherit pkgconfig cmake

DESCRIPTION = "the demo for warnning driver`s dangerous action"
LICENSE = "BSD"
SECTION = "ZLMediaKit"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

# Dependencies.
DEPENDS := "openssl"

FILESPATH =+ "${WORKSPACE}/example/ZLMediaKit/bin/:"

PN = "zlmedia_kit"
SRC_URI = "file://ZLMediaKit/"
INSANE_SKIP_${PN}-dev += "ldflags dev-elf dev-deps"
PACKAGES = "${PN}-dbg ${PN} ${PN}-dev"
S = "${WORKSPACE}/example/ZLMediaKit"

# Install directries.
#INSTALL_INCDIR := "${includedir}"
#INSTALL_BINDIR := "${bindir}"
#INSTALL_LIBDIR := "${libdir}"

#EXTRA_OECMAKE += ""

#FILES_${PN} += "${INSTALL_BINDIR}"
#FILES_${PN} += "${INSTALL_LIBDIR}"

#SOLIBS = ".so*"
#FILES_SOLIBSDEV = ""
