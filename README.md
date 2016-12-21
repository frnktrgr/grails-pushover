#grails-pushover

Provide easy access to Pushover API

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
		token: <APITOKEN>
		defaultUser: <DEFAULTUSER>
```
Get your free API Token from [Pushover](https://pushover.net).
The specified `token` is used in every Pushover call if no explicit token option is given.
If `pushoverService.message()` is called without a user/group token, the `defaultUser` is used. 

##Getting started
Send message `hello world` to `defaultUser` with configured `token` (see Configuration).
```groovy
pushoverService.message("hello world")
```

Send message `hello world` to `<USER/GROUP TOKEN>`.
```groovy
pushoverService.message("hello world", [user: '<USER/GROUP TOKEN>'])
```

##API
All methods and options are named after their Pushover API counterparts. Please read [Pushover API](https://pushover.net/api).

```groovy
pushoverService.message(String message, Map options=[:])
```
- `message`: your message
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
  - `user`: the user/group key (optional if `defaultUser` in config is set)
  - `device`: see [Pushover API](https://pushover.net/api) (optional)
  - `title`: see [Pushover API](https://pushover.net/api) (optional)
  - `url`: see [Pushover API](https://pushover.net/api) (optional)
  - `url_title`: see [Pushover API](https://pushover.net/api) (optional)
  - `priority`: see [Pushover API](https://pushover.net/api) (optional)
  - `timestamp`: see [Pushover API](https://pushover.net/api) (optional)
  - `sound`: see [Pushover API](https://pushover.net/api) (optional)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

```groovy
pushoverService.sounds(Map options=[:])
```
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

```groovy
pushoverService.validateUser(String user, Map options=[:])
```
- `user`: the user/group key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
  - `device`: see [Pushover API](https://pushover.net/api) (optional)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

```groovy
pushoverService.groups(String group, Map options=[:])
```
- `group`: group key
- `options`
  - `token`: your application's API token (optional if `token` in config is set)
- return value: map of Pushover response (see <https://pushover.net/api#response>)

```groovy
pushoverService.()
```
```groovy
pushoverService.()
```
```groovy
pushoverService.()
```
```groovy
pushoverService.()
```
```groovy
pushoverService.()
```
##Logging (Config.groovy)
```groovy
debug 'grails.plugin.pushover',
      'grails.app.services.grails.plugin.pushover'
```

##TODOs
See also https://pushover.net/api
- [ ] obey limitations https://pushover.net/api#limits
- [ ] check response https://pushover.net/api#response
- [ ] Receipts and Callbacks https://pushover.net/api#receipt
- [ ] Being Friendly to our API https://pushover.net/api#friendly
- [ ] Subscription API https://pushover.net/api/subscriptions
- [ ] Groups API https://pushover.net/api/groups
- [ ] Licensing API https://pushover.net/api/licensing
