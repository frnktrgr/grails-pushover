grails {
	pushover {
		scheme="https"
		host="api.pushover.net"
		messagePath="/1/messages.json"
		soundsPath="/1/sounds.json"
		usersValidatePath="/1/users/validate.json"
		groupsPath="/1/groups/"
		groupsInfoPostfix=".json"
		groupsAddUserPostfix="/add_user.json"
		groupsDeleteUserPostfix="/delete_user.json"
		groupsDisableUserPostfix="/disable_user.json"
		groupsEnableUserPostfix="/enable_user.json"
		groupsRenamePostfix="/rename.json"
	}
}
