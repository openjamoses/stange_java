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

import com.gluonhq.strange.gate.Cnot;
import com.gluonhq.strange.gate.Cz;
import com.gluonhq.strange.gate.Hadamard;
import com.gluonhq.strange.gate.Identity;
import com.gluonhq.strange.gate.InformalGate;
import com.gluonhq.strange.gate.Measurement;
import com.gluonhq.strange.gate.Oracle;
import com.gluonhq.strange.gate.PermutationGate;
import com.gluonhq.strange.gate.ProbabilitiesGate;
import com.gluonhq.strange.gate.Swap;
import com.gluonhq.strange.gate.Toffoli;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.gate.Y;
import com.gluonhq.strange.gate.Z;

import java.util.List;

/**
 *
 * A Gate describes an operation on one or more qubits.
 * @author johan
 */
public interface Gate {

    static Gate cnot(int a, int b) { return new Cnot(a, b); }
    static Gate cz(int a, int b)   { return new Cz(a, b); }
    static Gate hadamard(int idx)  { return new Hadamard(idx); }
    static Gate identity(int idx) { return new Identity(idx); }
    static Gate measurement(int idx) { return new Measurement(idx); }
    static Gate oracle(int idx) { return new Oracle(idx); }
    static Gate oracle(Complex[][] matrix) { return new Oracle(matrix); }
    static Gate permutation(int a, int b, int n) { return new PermutationGate(a, b, n); }
    static Gate probability(int idx) { return new ProbabilitiesGate(idx); }
    static Gate swap(int a, int b) { return new Swap(a, b); }
    static Gate toffoli(int a, int b, int c) { return new Toffoli(a, b, c); }
    static Gate x(int idx)  { return new X(idx); }
    static Gate y(int idx)  { return new Y(idx); }
    static Gate z(int idx)  { return new Z(idx); }

    /**
     * Set the main qubit where this gate operates on.
     * @param idx the index of the main qubit
     */
    public void setMainQubitIndex(int idx);
    
    /**
     * Return the index of the main qubit this gate acts upon
     * @return the index of the main qubit
     */
    public int getMainQubitIndex();
    
    public void setAdditionalQubit(int idx, int cnt);
    
    public List<Integer> getAffectedQubitIndexes();

    /**
     * Get the highest index of the qubit that is affected by this gate
     * @return the index of the highest affected qubit
     */
    public int getHighestAffectedQubitIndex();

    public String getCaption();
    
    public String getName();
    
    public String getGroup();
    
    public  Complex[][] getMatrix();

    
}
