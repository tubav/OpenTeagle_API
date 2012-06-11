#!/bin/bash
# Author: Alexander Willner <alexander.willner@tu-berlin.de>
# Todo: Refactor
#       

fokus_user="awi"
fokus_server="sshsrv.fokus.fraunhofer.de"

raven_server="192.168.144.12"
raven_user="teagle"
raven_repo_ui_port="8000"
raven_repo_ui_screen="gui"
raven_repo_ui_start="sh /opt/teagle/bin/djeagle"
raven_repo_db_port="8080"
raven_repo_db_screen="teagle"
raven_repo_db_start="sh /opt/teagle/bin/teaglerepo"
raven_repo_db_url="repository/rest"
raven_ptm_name="fokusptm"
raven_ptm_port="8010"
raven_ptm_screen="ptm"
raven_ptm_start="sh /opt/ptm/bin/ptmhub -p ${raven_ptm_port} -g http://localhost:${raven_repo_ui_port}/teaglegw -x ${raven_ptm_name}"
raven_ptm_url="reqproc"

av_server="teagle.av.tu-berlin.de"
av_user="root"
av_repo_ui_port="8000"
av_repo_ui_screen="repogui"
av_repo_ui_start="${raven_repo_ui_start}"
av_repo_db_port="8080"
av_repo_db_screen="repository"
av_repo_db_start="${raven_repo_db_start}"
av_repo_db_url="repository/rest"
av_ptm_name="avptm"
av_ptm_port="8010"
av_ptm_screen="ptm"
## no teaglegw here?
av_ptm_start="sh /opt/ptm/bin/ptmhub -p ${av_ptm_port} -x ${av_ptm_name}"
av_ptm_url="reqproc"

#pt_server="ptmatcher.av.tu-berlin.de"
pt_server="ptmatcher.service.tu-berlin.de"
pt_user="root"
pt_matcher_port="40123"
	
local_repo_ui_port="9000"
local_ptm_port="9010"
local_repo_db_port="9080"
local_matcher_port="40123"

parseargs () {
    found="0"
	for p in "$@"; do
		case "$p" in
			"showURLs")
				showURLs
				found="1"
				break
				;;
			"setupPortForwardingFokus")
				setupPortForwardingFokus
				found="1"
				break
				;;
			"setupPortForwardingPT")
				setupPortForwardingPT
				found="1"
				break
				;;
			"showLogFokusPTM")
				showLogFokusPTM
				found="1"
				break
				;;
			"showLogFokusRepoGUI")
				showLogFokusRepoGUI
				found="1"
				break
				;;
			"showLogFokusRepoDB")
				showLogFokusRepoDB
				found="1"
				break
				;;
			"loginTub")
				loginTub
				found="1"
				break
				;;
			"loginFokus")
				loginFokus
				found="1"
				break
				;;
			"loginPT")
				loginPT
				found="1"
				break
				;;
		esac
	done
	if [ "0" == "${found}" ]; then
		echo "Usage: $0 <command>"
		echo " * showURLs                 : show available URLs"
		echo " * loginFokus               : login on the FOKUS testbed"
		echo " * loginTub                 : login on the TUB testbed"
		echo " * loginPT                  : login on the PT testbed"
		echo " * setupPortForwardingFokus : forward ports for the FOKUS testbed"
		echo " * setupPortForwardingPT    : forward ports for the PT testbed"
		echo " * showLogFokusPTM          : show FOKUS PTM log"
		echo " * showLogFokusRepoGUI      : show FOKUS Repo GUI log"
		echo " * showLogFokusRepoDB       : show FOKUS Repo DB log"
		exit 1
	fi
}

printGeneralCommands () {
    echo " * To list the current screens:"
    echo "screen -ls"
}

showURLs () {
	echo " * AV - Repository GUI: http://${av_server}:${av_repo_ui_port}/"
	echo " * AV - Repository DB: http://${av_server}:${av_repo_db_port}/${av_repo_db_url}"
	echo " * AV - PTM: http://${av_server}:${av_ptm_port}/${av_ptm_url}"
	echo "-------- after running  setupPortForwardingFokus --------"
	echo " * FOKUS - Repository GUI: http://localhost:${local_repo_ui_port}/"
	echo " * FOKUS - Repository DB: http://localhost:${local_repo_db_port}/${raven_repo_db_url}"
	echo " * FOKUS - PTM: http://localhost:${local_ptm_port}/${raven_ptm_url}"
}

showLogFokusPTM () {
	ssh -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    -t "${raven_user}"@"${raven_server}" screen -x "${raven_ptm_screen}"
}

showLogFokusRepoGUI () {
	ssh -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    -t "${raven_user}"@"${raven_server}" sudo screen -x "${raven_repo_ui_screen}"
}

showLogFokusRepoDB () {
	ssh -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    -t "${raven_user}"@"${raven_server}" sudo screen -x "${raven_repo_db_screen}"
}

setupPortForwardingFokus () {
	echo "Tunneling ${raven_server}:${raven_repo_ui_port} to localhost:${local_repo_ui_port} ..."
	echo "Tunneling ${raven_server}:${raven_ptm_port} to localhost:${local_ptm_port} ..."
	echo "Tunneling ${raven_server}:${raven_repo_db_port} to localhost:${local_repo_db_port}..."

	ssh \
 	  -L ${local_repo_ui_port}:"${raven_server}":"${raven_repo_ui_port}" \
 	  -L ${local_ptm_port}:"${raven_server}":"${raven_ptm_port}" \
 	  -L ${local_repo_db_port}:"${raven_server}":"${raven_repo_db_port}" \
 	  "${fokus_user}"@"${fokus_server}" 
}

setupPortForwardingPT () {
	echo "Tunneling ${pt_server}:${pt_matcher_port} to localhost:${local_matcher_port} ..."

	ssh \
 	  -R "${local_matcher_port}":localhost:"${pt_matcher_port}" \
 	  "${pt_user}"@"${pt_server}" 
}


loginFokus () {
    clear
    printGeneralCommands
    echo "------------------------------------------------------------------------------"
    echo " * To start the PTM:"
    echo "screen -x ${raven_ptm_screen}"
    echo "${raven_ptm_start}"
    echo "------------------------------------------------------------------------------"
    echo " * To start the Repository DB (on port ${raven_repo_db_port}):"
    echo "sudo su -"
    echo "screen -x ${raven_repo_db_screen}"
    echo "${raven_repo_db_start}"
    echo "------------------------------------------------------------------------------"
    echo " * To start the Repository UI (on port ${raven_repo_ui_port}):"
    echo "sudo su -"
    echo "screen -x ${raven_repo_ui_screen}"
    echo "${raven_repo_ui_start}"
    echo "------------------------------------------------------------------------------"
    echo " * Slice details: sfi resources teagle.teagle.VCT_SLICENAME"
    echo " * Slice list: sfi list teagle.teagle"
    ehco " * Slice deletion: sfi remove teagle.teagle.VCT_SLICENAME"
    echo "------------------------------------------------------------------------------"
    echo "Logging into Fokus server..."
	ssh \
	  -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    "${raven_user}"@"${raven_server}"
}

loginPT () {
    clear
    printGeneralCommands
    echo "Logging into PT server..."
	ssh "${pt_user}"@"${pt_server}" 
}

loginTub () {
    clear
    printGeneralCommands
    echo "------------------------------------------------------------------------------"
    echo " * To start the PTM:"
    echo "screen -x ${av_ptm_screen}"
    echo "${av_ptm_start}"
    echo "------------------------------------------------------------------------------"
    echo " * To start the Repository DB (on port ${av_repo_db_port}):"
    echo "sudo su -"
    echo "screen -x ${av_repo_db_screen}"
    echo "${av_repo_db_start}"
    echo "------------------------------------------------------------------------------"
    echo " * To start the Repository UI (on port ${av_repo_ui_port}):"
    echo "sudo su -"
    echo "screen -x ${av_repo_ui_screen}"
    echo "${av_repo_ui_start}"
    echo "------------------------------------------------------------------------------"
    echo " * To have a look at the slice:"
    echo "sfi resources teagle.teagle.VCT_SLICENAME"
    echo "------------------------------------------------------------------------------"
    echo "Logging into the AV server..."
	ssh "${av_user}"@"${av_server}"
}

parseargs "$@"
