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
			"accessFokusPTM")
				accessFokusPTM
				found="1"
				break
				;;
			"accessFokusRepoGUI")
				accessFokusRepoGUI
				found="1"
				break
				;;
			"accessFokusRepoDB")
				accessFokusRepoDB
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
		esac
	done
	if [ "0" == "${found}" ]; then
		echo "Usage: $0 <command>"
		echo " * showURLs"
		echo " * loginFokus"
		echo " * loginTub"
		echo " * setupPortForwardingFokus"
		echo " * accessFokusPTM"
		echo " * accessFokusRepoGUI"
		echo " * accessFokusRepoDB"
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
	echo " * FOKUS - Repository GUI: run accessFokusRepoGUI"
	echo " * FOKUS - Repository DB: run accessFokusRepoDB"
	echo " * FOKUS - PTM: run accessFokusPTM"
	echo " * FOKUS - Run setupPortForwardingFokus to forward all ports"
}

accessFokusPTM () {
	echo "Tunneling http://${raven_server}:${raven_ptm_port} to http://localhost:9010 ..."
	ssh \
	  -L 9010:"${raven_server}":"${raven_ptm_port}" -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    -t "${raven_user}"@"${raven_server}" screen -x "${raven_ptm_screen}"
}

accessFokusRepoGUI () {
	echo "Tunneling http://${raven_server}:${raven_repo_ui_port} to http://localhost:9000 ..."
	ssh \
	  -L 9000:"${raven_server}":"${raven_repo_ui_port}" -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    -t "${raven_user}"@"${raven_server}" sudo screen -x "${raven_repo_ui_screen}"
}

accessFokusRepoDB () {
	echo "Tunneling http://${raven_server}:${raven_repo_db_port} to http://localhost:9080/${raven_repo_db_url} ..."
	ssh \
	  -L 9080:"${raven_server}":"${raven_repo_db_port}" -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    -t "${raven_user}"@"${raven_server}" sudo screen -x "${raven_repo_db_screen}"
}

setupPortForwardingFokus () {
	echo "Tunneling ${raven_server}:${raven_repo_ui_port} to localhost:9000 ..."
	echo "Tunneling ${raven_server}:${raven_ptm_port} to localhost:9010 ..."
	echo "Tunneling ${raven_server}:${raven_repo_db_port} to localhost:9080..."

	ssh \
 	  -L 9000:"${raven_server}":"${raven_repo_ui_port}" \
 	  -L 9010:"${raven_server}":"${raven_ptm_port}" \
 	  -L 9080:"${raven_server}":"${raven_repo_db_port}" \
 	  "${fokus_user}"@"${fokus_server}" 
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
    echo " * To have a look at the slice:"
    echo "sfi resources teagle.teagle.VCT_SLICENAME"
    echo "------------------------------------------------------------------------------"
    echo "Logging into Fokus server..."
	ssh \
	  -t "${fokus_user}"@"${fokus_server}" \
	  ssh -o GSSAPIAuthentication=no \
	    "${raven_user}"@"${raven_server}"
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
