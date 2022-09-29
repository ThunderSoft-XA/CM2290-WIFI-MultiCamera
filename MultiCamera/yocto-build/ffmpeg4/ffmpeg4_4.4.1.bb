SUMMARY = "A complete, cross-platform solution to record, convert and stream audio and video."
DESCRIPTION = "FFmpeg is the leading multimedia framework, able to decode, encode, transcode, \
               mux, demux, stream, filter and play pretty much anything that humans and machines \
               have created. It supports the most obscure ancient formats up to the cutting edge."
HOMEPAGE = "https://www.ffmpeg.org/"
SECTION = "libs"

LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later & ISC & MIT & BSD-2-Clause & BSD-3-Clause & IJG"
LICENSE:${PN} = "GPL-2.0-or-later"
LICENSE:libavcodec4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libavdevice4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libavfilter4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libavformat4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libavresample4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libavutil4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libpostproc4 = "GPL-2.0-or-later"
LICENSE:libswresample4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE:libswscale4 = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPL-2.0-or-later', 'LGPL-2.1-or-later', d)}"
LICENSE_FLAGS = "commercial"

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://www.ffmpeg.org/releases/ffmpeg-${PV}.tar.xz \
           file://0001-libavutil-include-assembly-with-full-path-from-sourc.patch \
           "
SRC_URI[sha256sum] = "eadbad9e9ab30b25f5520fbfde99fae4a92a1ae3c0257a8d68569a4651e30e02"
S = "${WORKDIR}/ffmpeg-${PV}"

# Build fails when thumb is enabled: https://bugzilla.yoctoproject.org/show_bug.cgi?id=7717
ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"
ARM_INSTRUCTION_SET:armv6 = "arm"

DEPENDS = "nasm-native"

inherit autotools pkgconfig

PACKAGECONFIG ??= "avdevice avfilter avcodec avformat swresample swscale postproc avresample \
                   alsa bzlib lzma pic pthreads shared theora zlib \
                   ${@bb.utils.contains('AVAILTUNES', 'mips32r2', 'mips32r2', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xv xcb', '', d)}"

# libraries to build in addition to avutil
PACKAGECONFIG[avdevice] = "--enable-avdevice,--disable-avdevice"
PACKAGECONFIG[avfilter] = "--enable-avfilter,--disable-avfilter"
PACKAGECONFIG[avcodec] = "--enable-avcodec,--disable-avcodec"
PACKAGECONFIG[avformat] = "--enable-avformat,--disable-avformat"
PACKAGECONFIG[swresample] = "--enable-swresample,--disable-swresample"
PACKAGECONFIG[swscale] = "--enable-swscale,--disable-swscale"
PACKAGECONFIG[postproc] = "--enable-postproc,--disable-postproc"
PACKAGECONFIG[avresample] = "--enable-avresample,--disable-avresample"

# features to support
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[altivec] = "--enable-altivec,--disable-altivec,"
PACKAGECONFIG[bzlib] = "--enable-bzlib,--disable-bzlib,bzip2"
PACKAGECONFIG[fdk-aac] = "--enable-libfdk-aac --enable-nonfree,--disable-libfdk-aac,fdk-aac"
PACKAGECONFIG[gpl] = "--enable-gpl,--disable-gpl"
PACKAGECONFIG[gsm] = "--enable-libgsm,--disable-libgsm,libgsm"
PACKAGECONFIG[jack] = "--enable-indev=jack,--disable-indev=jack,jack"
PACKAGECONFIG[libopus] = "--enable-libopus,--disable-libopus,libopus"
PACKAGECONFIG[libvorbis] = "--enable-libvorbis,--disable-libvorbis,libvorbis"
PACKAGECONFIG[lzma] = "--enable-lzma,--disable-lzma,xz"
PACKAGECONFIG[mfx] = "--enable-libmfx,--disable-libmfx,intel-mediasdk"
PACKAGECONFIG[mp3lame] = "--enable-libmp3lame,--disable-libmp3lame,lame"
PACKAGECONFIG[openssl] = "--enable-openssl,--disable-openssl,openssl"
PACKAGECONFIG[sdl2] = "--enable-sdl2,--disable-sdl2,virtual/libsdl2"
PACKAGECONFIG[speex] = "--enable-libspeex,--disable-libspeex,speex"
PACKAGECONFIG[srt] = "--enable-libsrt,--disable-libsrt,srt"
PACKAGECONFIG[theora] = "--enable-libtheora,--disable-libtheora,libtheora libogg"
PACKAGECONFIG[vaapi] = "--enable-vaapi,--disable-vaapi,libva"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"
PACKAGECONFIG[vpx] = "--enable-libvpx,--disable-libvpx,libvpx"
PACKAGECONFIG[x264] = "--enable-libx264,--disable-libx264,x264"
PACKAGECONFIG[x265] = "--enable-libx265,--disable-libx265,x265"
PACKAGECONFIG[xcb] = "--enable-libxcb,--disable-libxcb,libxcb"
PACKAGECONFIG[xv] = "--enable-outdev=xv,--disable-outdev=xv,libxv"
PACKAGECONFIG[zlib] = "--enable-zlib,--disable-zlib,zlib"

# other configuration options
PACKAGECONFIG[mips32r2] = ",--disable-mipsdsp --disable-mipsdspr2"
PACKAGECONFIG[pic] = "--enable-pic"
PACKAGECONFIG[pthreads] = "--enable-pthreads,--disable-pthreads"
PACKAGECONFIG[shared] = "--enable-shared"
PACKAGECONFIG[strip] = ",--disable-stripping"

# Check codecs that require --enable-nonfree
USE_NONFREE = "${@bb.utils.contains_any('PACKAGECONFIG', [ 'openssl' ], 'yes', '', d)}"

def cpu(d):
    for arg in (d.getVar('TUNE_CCARGS') or '').split():
        if arg.startswith('-mcpu='):
            return arg[6:]
    return 'generic'

EXTRA_OECONF = " \
    ${@bb.utils.contains('USE_NONFREE', 'yes', '--enable-nonfree', '', d)} \
    \
    --cross-prefix=${TARGET_PREFIX} \
    \
    --ld='${CCLD}' \
    --cc='${CC}' \
    --cxx='${CXX}' \
    --arch=${TARGET_ARCH} \
    --target-os='linux' \
    --enable-cross-compile \
    --extra-cflags='${CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}' \
    --extra-ldflags='${LDFLAGS}' \
    --sysroot='${STAGING_DIR_TARGET}' \
    ${EXTRA_FFCONF} \
    --libdir=${libdir} \
    --shlibdir=${libdir} \
    --datadir=${datadir}/ffmpeg4 \
    --cpu=${@cpu(d)} \
    --pkg-config=pkg-config \
    --build-suffix=4 \
    --progs-suffix=4 \
    --incdir=${includedir}/ffmpeg4 \
"

EXTRA_OECONF:append:linux-gnux32 = " --disable-asm"

EXTRA_OECONF += "${@bb.utils.contains('TUNE_FEATURES', 'mipsisa64r6', '--disable-mips64r2 --disable-mips32r2', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains('TUNE_FEATURES', 'mipsisa64r2', '--disable-mips64r6 --disable-mips32r6', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains('TUNE_FEATURES', 'mips32r2', '--disable-mips64r6 --disable-mips32r6', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains('TUNE_FEATURES', 'mips32r6', '--disable-mips64r2 --disable-mips32r2', '', d)}"
EXTRA_OECONF:append:mips = " --extra-libs=-latomic --disable-mips32r5 --disable-mipsdsp --disable-mipsdspr2 \
                             --disable-loongson2 --disable-loongson3 --disable-mmi --disable-msa --disable-msa2"
EXTRA_OECONF:append:riscv32 = " --extra-libs=-latomic"
EXTRA_OECONF:append:armv5 = " --extra-libs=-latomic"
EXTRA_OECONF:append:powerpc = " --extra-libs=-latomic"

# gold crashes on x86, another solution is to --disable-asm but thats more hacky
# ld.gold: internal error in relocate_section, at ../../gold/i386.cc:3684

LDFLAGS:append:x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

EXTRA_OEMAKE = "V=1"

do_configure() {
    ${S}/configure ${EXTRA_OECONF}
}

# patch out build host paths for reproducibility
do_compile:prepend:class-target() {
        sed -i -e "s,${WORKDIR},,g" ${B}/config.h
}

PACKAGES =+ "libavcodec4 \
             libavdevice4 \
             libavfilter4 \
             libavformat4 \
             libavresample4 \
             libavutil4 \
             libpostproc4 \
             libswresample4 \
             libswscale4"

FILES:libavcodec4 = "${libdir}/libavcodec4${SOLIBS}"
FILES:libavdevice4 = "${libdir}/libavdevice4${SOLIBS}"
FILES:libavfilter4 = "${libdir}/libavfilter4${SOLIBS}"
FILES:libavformat4 = "${libdir}/libavformat4${SOLIBS}"
FILES:libavresample4 = "${libdir}/libavresample4${SOLIBS}"
FILES:libavutil4 = "${libdir}/libavutil4${SOLIBS}"
FILES:libpostproc4 = "${libdir}/libpostproc4${SOLIBS}"
FILES:libswresample4 = "${libdir}/libswresample4${SOLIBS}"
FILES:libswscale4 = "${libdir}/libswscale4${SOLIBS}"

# ffmpeg disables PIC on some platforms (e.g. x86-32)
INSANE_SKIP:${MLPREFIX}libavcodec4 = "textrel"
INSANE_SKIP:${MLPREFIX}libavdevice4 = "textrel"
INSANE_SKIP:${MLPREFIX}libavfilter4 = "textrel"
INSANE_SKIP:${MLPREFIX}libavformat4 = "textrel"
INSANE_SKIP:${MLPREFIX}libavutil4 = "textrel"
INSANE_SKIP:${MLPREFIX}libavresample4 = "textrel"
INSANE_SKIP:${MLPREFIX}libswscale4 = "textrel"
INSANE_SKIP:${MLPREFIX}libswresample4 = "textrel"
INSANE_SKIP:${MLPREFIX}libpostproc4 = "textrel"
