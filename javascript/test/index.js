"use strict";

const mocha = require('mocha');
const it = mocha.it;
const describe = mocha.describe;
const sinon = require('sinon');
const hamjest = require('hamjest');
const assertThat = hamjest.assertThat;
const equalTo = hamjest.equalTo;
const assert = require('chai').assert;


const sandbox = sinon.sandbox.create();
const stub = sandbox.stub.bind(sandbox);
const spy = sandbox.spy.bind(sandbox);

afterEach(function () {
    sandbox.reset();
});

describe('stuff', function () {
    const productionCode = {
        add: function (one, two) {
            return one + two;
        },

        throwException: function () {
            throw 'some exception';
        }
    };

    describe('stub actual code for the test', function () {

        it('production code', function () {
            assertThat(productionCode.add(1, 2), equalTo(3));
        });

        it('alternative function', function () {
            const add = stub(productionCode, 'add', function (one, two) {
                return 42;
            });

            assertThat(productionCode.add(1, 2), equalTo(42));
        })
    });

    describe('forwarding to production code', function () {
        it('production code', function () {
            assert.throws(productionCode.throwException, 'some exception');
        });
    });

});