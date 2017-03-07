"use strict";

const mocha = require('mocha');
const it = mocha.it;
const describe = mocha.describe;
const sinon = require('sinon');
const hamjest = require('hamjest');
const assertThat = hamjest.assertThat;
const equalTo = hamjest.equalTo;
const chai = require('chai');
const assert = chai.assert;
var sinonChai = require("sinon-chai");
const expect = chai.expect;

chai.use(sinonChai);
sinon.assert.expose(chai.assert, {prefix: ""});


const sandbox = sinon.sandbox.create();
const stub = sandbox.stub.bind(sandbox);
const spy = sandbox.spy.bind(sandbox);

afterEach(function () {
    sandbox.reset();
});

describe('make a method return a value', function () {

    describe('sinon', function () {

        it('subsequent calls to return or throws override each other', function () {
            const stub = sinon.createStubInstance(Array);
            stub.pop.returns('nope');
            stub.pop.returns('yes');

            assert.equal(stub.pop(), 'yes');
            assert.equal(stub.pop(), 'yes');
            assert.equal(stub.pop(), 'yes');
        });

        it('different on subsequent calls', function () {
            const stub = sinon.createStubInstance(Array);
            stub.pop
                .onCall(0).returns('last element')
                .onSecondCall().returns('second call');

            assert.equal(stub.pop(), 'last element');
            assert.equal(stub.pop(), 'second call');
            assert.equal(stub.pop(), undefined);
        });

        it('argument specific returns', function () {
            const stub = sinon.createStubInstance(Array);
            stub.slice.withArgs(1, 2).returns(['one', 'two']);
            stub.slice.withArgs(1).returns(['one']);
            stub.slice.withArgs(2).returns(['two']);

            assert.sameMembers(stub.slice(1), ['one'])
            assert.sameMembers(stub.slice(2), ['two'])
            assert.sameMembers(stub.slice(1, 2), ['one', 'two'])
        });
    });
});