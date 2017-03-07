"use strict";

const mocha = require('mocha');
const it = mocha.it;
const describe = mocha.describe;
const sinon = require('sinon');
const hamjest = require('hamjest');
const chai = require('chai');
var sinonChai = require("sinon-chai");
const expect = chai.expect;

chai.use(sinonChai);
sinon.assert.expose(chai.assert, {prefix: ""});

const sandbox = sinon.sandbox.create();

afterEach(function () {
    sandbox.reset();
});

describe('verify a method was called', function () {

    it('sinon', function () {
        const stub = sinon.createStubInstance(Array);

        stub.push('expected argument');
        expect(stub.push).calledWith('expected argument')
    });
});