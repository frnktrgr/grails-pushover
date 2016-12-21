# grails-pushover

Provides easy access to Pushover API.

## Installation
Add following line to `dependencies` section in `build.gradle`.
```gradle
compile 'org.grails.plugins:pushover:1.0.1'
```

## Configuration
Add following lines to `grails-app/conf/application.yml`:
```yaml
grails:
	pushover:
		token: <API_TOKEN>
		defaultUser: <DEFAULT_USER>
```
Get your free API Token from [Pushover](https://pushover.net).

The specified `token` is used in every Pushover call if no explicit token option is given.

If `pushoverService.message()` is called without a user/group token, the `defaultUser` is used. 

## Getting started

### Send message to default user
Send message `hello world` to `defaultUser` with configured `token` (see Configuration).
```groovy
pushoverService.message("hello world")
```

### Send message to some user/group
Send message `hello world` to `<USER/GROUP_TOKEN>`.
```groovy
pushoverService.message("hello world", [user: '<USER/GROUP_TOKEN>'])
```

### Send message using another API token
Send message `hello world` using `<ANOTHER_API_TOKEN>` API token.
```groovy
pushoverService.message("hello world", [token: '<ANOTHER_API_TOKEN>'])
```

## API
All methods and options are named after their Pushover API counterparts. Please read [Pushover API](https://pushover.net/api).

### Send message
```groovy
pushoverService.message(String message, Map options=[:])
```
- `message`: your message
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
  - `user`: the user/group key (optional if `defaultUser` in config is set)
  - `device`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
  - `title`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
  - `url`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
  - `url_title`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
  - `priority`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
  - `timestamp`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
  - `sound`: see [Pushover Message API](https://pushover.net/api#messages) (optional)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Retrieve the list of current sounds
```groovy
pushoverService.sounds(Map options=[:])
```
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### User/Group verification
```groovy
pushoverService.validateUser(String user, Map options=[:])
```
- `user`: the user/group key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
  - `device`: see [Pushover API](https://pushover.net/api) (optional)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Retrieve information about a group
```groovy
pushoverService.groups(String group, Map options=[:])
```
- `group`: group key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Adding a user to a group
```groovy
pushoverService.groupsAddUser(String group, String user, Map options=[:])
```
- `group`: group key
- `user`: user key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
  - `device`: see [Pushover Groups API](https://pushover.net/api/groups#add_user) (optional)
  - `memo`: see [Pushover Groups API](https://pushover.net/api/groups#add_user) (optional)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Removing a user from a group
```groovy
pushoverService.groupsDeleteUser(String group, String user, Map options=[:])
```
- `group`: group key
- `user`: user key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Temporarily disabling a user in a group
```groovy
pushoverService.groupsDisableUser(String group, String user, Map options=[:])
```
- `group`: group key
- `user`: user key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Re-enabling a user in a group
```groovy
pushoverService.groupsEnableUser(String group, String user, Map options=[:])
```
- `group`: group key
- `user`: user key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

### Renaming a group
```groovy
pushoverService.groupsRename(String group, String name, Map options=[:])
```
- `group`: group key
- `name`: new name of the group
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

##TODOs
See also https://pushover.net/api
- [ ] obey limitations https://pushover.net/api#limits
- [ ] check response https://pushover.net/api#response
- [ ] Receipts and Callbacks https://pushover.net/api#receipt
- [ ] Being Friendly to our API https://pushover.net/api#friendly
- [ ] Subscription API https://pushover.net/api/subscriptions
- [ ] Licensing API https://pushover.net/api/licensing
