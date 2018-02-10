'''
@author:
@create:
'''
import re
import time
import hashlib
import datetime


def md5sum(src):
    m = hashlib.md5()
    m.update(src)
    return m.hexdigest()


def a_auth(uri, key, exp):
    p = re.compile("^(http://|https://)?([^/?]+)(/[^?]*)?(\\?.*)?$")
    if not p:
        return None
    m = p.match(uri)
    scheme, host, path, args = m.groups()
    if not scheme: scheme = "http://"
    if not path: path = "/"
    if not args: args = ""
    rand = "0"  # "0" by default, other value is ok
    uid = "0"  # "0" by default, other value is ok
    sstring = "%s-%s-%s-%s-%s" % (path, exp, rand, uid, key)
    hashvalue = md5sum(sstring)
    auth_key = "%s-%s-%s-%s" % (exp, rand, uid, hashvalue)
    if args:
        return "%s%s%s%s&auth_key=%s" % (scheme, host, path, args, auth_key)
    else:
        return "%s%s%s%s?auth_key=%s" % (scheme, host, path, args, auth_key)


def main():
    uri = "http://xc.cdnpe.com/ping?foo=bar"  # original uri
    key = "<input private key>"  # private key of     authorization
    exp = int(time.time()) + 1 * 3600  # expiration     time: 1 hour after current itme
    authuri = a_auth(uri, key, exp)  # auth type:
    print("URL : %s\nAUTH: %s" % (uri, authuri))


if __name__ == "__main__":
    main()
