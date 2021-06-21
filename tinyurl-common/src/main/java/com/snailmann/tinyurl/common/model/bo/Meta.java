
package com.snailmann.tinyurl.common.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liwenjie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    private String originalAddress;

    private String tinyKey;

    private long ttl;

    private long creation;

    public boolean hasValue() {
        return StringUtils.isNotBlank(originalAddress);
    }

}