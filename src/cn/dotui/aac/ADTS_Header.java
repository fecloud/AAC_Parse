/**
 * ADTS.java Created on 2016年6月2日
 */
package cn.dotui.aac;

import java.io.IOException;
import java.io.InputStream;

/**
 * The class <code>ADTS</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ADTS_Header {

	public String MPEG_4_Audio_Object_Type[] = { "Null", "AAC Main", "AAC LC",
			"AAC SSR" };

	public String MPEG_4_Sampling_Frequency_Index[] = { "96000 Hz", "88200 Hz",
			"64000 Hz", "48000 Hz", "44100 Hz", "32000 Hz", "24000 Hz",
			"22050 Hz", "16000 Hz", "12000 Hz", "11025 Hz", "8000 Hz",
			"7350 Hz", "Reserved", "Reserved", "frequency is written explictly" };

	/**
	 * 12
	 */
	private int syncword;

	/**
	 * 1
	 */
	private int id;

	/**
	 * 2 always: '00'
	 */
	private int layer;

	/**
	 * 1
	 */
	private int protection_absent;

	/**
	 * 2
	 */
	private int profile;

	/**
	 * 4
	 */
	private int sampling_frequency_index;

	/**
	 * 1
	 */
	private int private_bit;

	/**
	 * 3
	 */
	private int channel_configuration;

	/**
	 * 1
	 */
	private int original_copy;

	/**
	 * 1
	 */
	private int home;

	/**
	 * 1
	 */
	private int copyrighted;

	/**
	 * 1
	 */
	private int copyright;

	/**
	 * 13
	 */
	private int frame_length;

	/**
	 * 11
	 */
	private int buffer_fullness;

	/**
	 * 2
	 */
	private int number;

	private byte[] raw;

	/**
	 * @return the syncword
	 */
	public int getSyncword() {
		return syncword;
	}

	/**
	 * @param syncword
	 *            the syncword to set
	 */
	public void setSyncword(int syncword) {
		this.syncword = syncword;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the layer
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * @param layer
	 *            the layer to set
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/**
	 * @return the protection_absent
	 */
	public int getProtection_absent() {
		return protection_absent;
	}

	/**
	 * @param protection_absent
	 *            the protection_absent to set
	 */
	public void setProtection_absent(int protection_absent) {
		this.protection_absent = protection_absent;
	}

	/**
	 * @return the profile
	 */
	public int getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(int profile) {
		this.profile = profile;
	}

	/**
	 * @return the sampling_frequency_index
	 */
	public int getSampling_frequency_index() {
		return sampling_frequency_index;
	}

	/**
	 * @param sampling_frequency_index
	 *            the sampling_frequency_index to set
	 */
	public void setSampling_frequency_index(int sampling_frequency_index) {
		this.sampling_frequency_index = sampling_frequency_index;
	}

	/**
	 * @return the private_bit
	 */
	public int getPrivate_bit() {
		return private_bit;
	}

	/**
	 * @param private_bit
	 *            the private_bit to set
	 */
	public void setPrivate_bit(int private_bit) {
		this.private_bit = private_bit;
	}

	/**
	 * @return the channel_configuration
	 */
	public int getChannel_configuration() {
		return channel_configuration;
	}

	/**
	 * @param channel_configuration
	 *            the channel_configuration to set
	 */
	public void setChannel_configuration(int channel_configuration) {
		this.channel_configuration = channel_configuration;
	}

	/**
	 * @return the original_copy
	 */
	public int getOriginal_copy() {
		return original_copy;
	}

	/**
	 * @param original_copy
	 *            the original_copy to set
	 */
	public void setOriginal_copy(int original_copy) {
		this.original_copy = original_copy;
	}

	/**
	 * @return the home
	 */
	public int getHome() {
		return home;
	}

	/**
	 * @param home
	 *            the home to set
	 */
	public void setHome(int home) {
		this.home = home;
	}

	/**
	 * @return the copyrighted
	 */
	public int getCopyrighted() {
		return copyrighted;
	}

	/**
	 * @param copyrighted
	 *            the copyrighted to set
	 */
	public void setCopyrighted(int copyrighted) {
		this.copyrighted = copyrighted;
	}

	/**
	 * @return the copyright
	 */
	public int getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	public void setCopyright(int copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the frame_length
	 */
	public int getFrame_length() {
		return frame_length;
	}

	/**
	 * @param frame_length
	 *            the frame_length to set
	 */
	public void setFrame_length(int frame_length) {
		this.frame_length = frame_length;
	}

	/**
	 * @return the buffer_fullness
	 */
	public int getBuffer_fullness() {
		return buffer_fullness;
	}

	/**
	 * @param buffer_fullness
	 *            the buffer_fullness to set
	 */
	public void setBuffer_fullness(int buffer_fullness) {
		this.buffer_fullness = buffer_fullness;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the raw
	 */
	public byte[] getRaw() {
		return raw;
	}

	/**
	 * @param raw
	 *            the raw to set
	 */
	public void setRaw(byte[] raw) {
		this.raw = raw;
	}

	public boolean readHeader(InputStream in) throws IOException {
		final byte[] head = new byte[7];
		if (head.length == in.read(head, 0, head.length)) {
			syncword = (head[0] & 0xFF);
			syncword <<= 4;
			int syncword_low = ((head[1] & 0xF0) >> 4);
			syncword |= syncword_low;

			id = ((head[1] & 0x0F) >> 3);

			layer = ((head[1] & 0x06) >> 1);

			protection_absent = ((head[1] & 0x01));

			profile = ((head[2] & 0xFF) >> 6);

			sampling_frequency_index = ((head[2] & 0x3F) >> 2);

			private_bit = ((head[2] & 0x1F) >> 1);

			channel_configuration = ((head[2] & 0x01));
			channel_configuration <<= 1;
			int channel_configuration_low = ((head[3] & 0xCF) >> 6);
			channel_configuration |= channel_configuration_low;

			original_copy = ((head[3] & 0x2F) >> 5);
			home = ((head[3] & 0x1F) >> 4);

			copyrighted = ((head[3] & 0x0F) >> 3);
			copyright = ((head[3] & 0x07) >> 2);

			frame_length = (head[3] & 0x03);
			frame_length <<= 8;
			frame_length |= head[4];
			frame_length <<= 3;

			int frame_length_low = ((head[5] & 0xFF) >> 5);
			frame_length |= frame_length_low;
			frame_length -= 7;

			buffer_fullness = head[5] & 0x1F;
			buffer_fullness <<= 6;
			int buffer_fullness_low = ((head[6] & 0xFF) >> 2);
			buffer_fullness |= buffer_fullness_low;

			number = (head[6] & 0x03);

			raw = new byte[frame_length];
			if (frame_length == in.read(raw)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ADTS_Header [syncword=" + syncword + ", id=" + id + ", layer="
				+ layer + ", protection_absent=" + protection_absent
				+ ", profile=" + MPEG_4_Audio_Object_Type[profile + 1]
				+ ", sampling_frequency_index="
				+ MPEG_4_Sampling_Frequency_Index[sampling_frequency_index]
				+ ", private_bit=" + private_bit + ", channel_configuration="
				+ channel_configuration + ", original_copy=" + original_copy
				+ ", home=" + home + ", copyrighted=" + copyrighted
				+ ", copyright=" + copyright + ", frame_length=" + frame_length
				+ ", buffer_fullness=" + buffer_fullness + ", number=" + number
				+ ", raw=" + raw.length + "]";
	}

}
