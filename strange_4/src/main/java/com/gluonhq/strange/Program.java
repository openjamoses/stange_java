/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2018, Gluon Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gluonhq.strange;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * A Quantum Program.
 * A Program contains a list of <code>Step</code>s that are executed sequentially 
 * by a QuantumExecutionEnvironment.
 * @author johan
 */
public class Program {
 
    private final int numberQubits;
    private Result result;
    private double[] initAlpha;

    private final ArrayList<Step> steps = new ArrayList<>();

    // cache decomposedSteps
    private List<Step> decomposedSteps = null;

    /**
     * Create a Quantum Program and indicate how many qubits will be involved.
     * By default, all qubits are initialized to the |0 &gt; state.
     * @param nQubits the amount of qubits that will be used in this program
     */
    public Program(int nQubits) {
        this.numberQubits = nQubits;
        this.initAlpha = new double[numberQubits];
        for (int i = 0; i < numberQubits; i++) {
            this.initAlpha[i] = 1d;
        }
    }

    /**
     * Initialize this qubit. The qubit will be in a state
     * \psi = \alpha |0 &gt; + \beta |1 &gt; .
     * Since \alpha^2 + \beta^2 should equals 1, only
     * \alpha is required.
     * @param idx the index of the qubit to be initialized
     * @param alpha the alpha value of the qubit state.
     * @throws IllegalArgumentException in case the index of the qubit is higher than the amount of qubits -1 .
     */
    public void initializeQubit(int idx, double alpha) {
        if (idx >= numberQubits) {
            throw new IllegalArgumentException("Can not initialize qubit "+
                    idx+" since we have only "+numberQubits+" qubits.");
        }
        this.initAlpha[idx] = alpha;
    }

    public double[] getInitialAlphas() {
        return this.initAlpha;
    }

    public void addStep (Step s) {
        s.setIndex(steps.size());
        s.setProgram(this);
        steps.add(s);
        this.decomposedSteps = null;
    }
    
    public List<Step> getSteps() {
        return this.steps;
    }

    public List<Step> getDecomposedSteps () {
        return this.decomposedSteps;
    }

    public void setDecomposedSteps(List<Step> ds) {
        this.decomposedSteps = ds;
    }
    
    public int getNumberQubits() {
        return this.numberQubits;
    }

    public void setResult(Result r) {
        this.result = r;
    }

    public Result getResult() {
        return this.result;
    }
    
}
