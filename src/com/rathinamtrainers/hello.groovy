package com.rathinamtrainers

def checkOutFrom(repo) {
  git url: "git@github.com:jenkinsci/${repo}"
}

def sayHi(String name = 'Student') {
  echo "Hi ${name}"
}

return this
