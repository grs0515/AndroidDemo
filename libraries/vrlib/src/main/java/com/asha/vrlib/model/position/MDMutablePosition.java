package com.asha.vrlib.model.position;

import android.opengl.Matrix;

import com.asha.vrlib.model.MDPosition;

import static com.asha.vrlib.common.VRUtil.checkGLThread;
import static com.asha.vrlib.common.VRUtil.notNull;

/**
 * Created by hzqiujiadi on 2017/4/11.
 * hzqiujiadi ashqalcn@gmail.com
 */

public class MDMutablePosition extends MDPosition {

    private float[] mModelMatrix = null;
    private float[] mRotationMatrix = null;
    private static final float[] sSharedTmpMatrix = new float[16];

    private float mX;
    private float mY;
    private float mZ;
    private float mAngleX;
    private float mAngleY;
    private float mAngleZ;
    private float mPitch; // x-axis
    private float mYaw; // y-axis
    private float mRoll; // z-axis
    private boolean changed;

    private MDMutablePosition() {
        mX = mY = mZ = 0;
        mAngleX = mAngleY = mAngleZ = 0;
        mPitch = mYaw = mRoll = 0;
        changed = true;
    }

    public float getPitch() {
        return mPitch;
    }

    public MDMutablePosition setPitch(float pitch) {
        this.mPitch = pitch;
        changed = true;
        return this;
    }

    public float getYaw() {
        return mYaw;
    }

    public MDMutablePosition setYaw(float yaw) {
        this.mYaw = yaw;
        changed = true;
        return this;
    }

    public float getRoll() {
        return mRoll;
    }

    public MDMutablePosition setRoll(float roll) {
        this.mRoll = roll;
        changed = true;
        return this;
    }

    public float getX() {
        return mX;
    }

    public MDMutablePosition setX(float x) {
        this.mX = x;
        changed = true;
        return this;
    }

    public float getY() {
        return mY;
    }

    public MDMutablePosition setY(float y) {
        this.mY = y;
        changed = true;
        return this;
    }

    public float getZ() {
        return mZ;
    }

    public MDMutablePosition setZ(float z) {
        this.mZ = z;
        changed = true;
        return this;
    }

    public float getAngleX() {
        return mAngleX;
    }

    /**
     * setAngleX
     * @param angleX in degree
     * @return self
     */
    public MDMutablePosition setAngleX(float angleX) {
        this.mAngleX = angleX;
        changed = true;
        return this;
    }

    public float getAngleY() {
        return mAngleY;
    }

    /**
     * setAngleY
     * @param angleY in degree
     * @return self
     */
    public MDMutablePosition setAngleY(float angleY) {
        this.mAngleY = angleY;
        changed = true;
        return this;
    }

    public float getAngleZ() {
        return mAngleZ;
    }

    /**
     * setAngleZ
     * @param angleZ in degree
     * @return self
     */
    public MDMutablePosition setAngleZ(float angleZ) {
        this.mAngleZ = angleZ;
        changed = true;
        return this;
    }

    public static MDMutablePosition newInstance(){
        return new MDMutablePosition();
    }

    @Override
    public String toString() {
        return "MDPosition{" +
                "mX=" + mX +
                ", mY=" + mY +
                ", mZ=" + mZ +
                ", mAngleX=" + mAngleX +
                ", mAngleY=" + mAngleY +
                ", mAngleZ=" + mAngleZ +
                ", mPitch=" + mPitch +
                ", mYaw=" + mYaw +
                ", mRoll=" + mRoll +
                '}';
    }

    private void ensure(){
        if (!changed){
            return;
        }

        // model
        if (mModelMatrix == null){
            mModelMatrix = new float[16];
        }

        Matrix.setIdentityM(mModelMatrix, 0);

        Matrix.rotateM(mModelMatrix, 0, getAngleY(), 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, getAngleX(), 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, getAngleZ(), 0.0f, 0.0f, 1.0f);

        Matrix.translateM(mModelMatrix, 0, getX(),getY(),getZ());

        Matrix.rotateM(mModelMatrix, 0, getYaw(), 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, getPitch(), 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, getRoll(), 0.0f, 0.0f, 1.0f);

        // rotation
        if (mRotationMatrix != null){
            Matrix.multiplyMM(sSharedTmpMatrix, 0,  mRotationMatrix, 0, mModelMatrix, 0);
            System.arraycopy(sSharedTmpMatrix, 0, mModelMatrix, 0, 16);
        }

        changed = false;
    }

    @Override
    public void setRotationMatrix(float[] rotationMatrix){
        notNull(rotationMatrix, "rotationMatrix can't be null!");
        checkGLThread("setRotationMatrix must called in gl thread!");

        if (mRotationMatrix == null){
            mRotationMatrix = new float[16];
        }

        System.arraycopy(rotationMatrix, 0, mRotationMatrix, 0, 16);
        changed = true;
    }

    @Override
    public float[] getMatrix() {
        ensure();
        return mModelMatrix;
    }
}
