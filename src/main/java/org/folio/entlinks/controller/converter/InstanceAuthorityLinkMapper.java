package org.folio.entlinks.controller.converter;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.folio.entlinks.domain.dto.InstanceLinkDto;
import org.folio.entlinks.domain.dto.InstanceLinkDtoCollection;
import org.folio.entlinks.domain.dto.LinksCountDto;
import org.folio.entlinks.domain.entity.InstanceAuthorityLink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstanceAuthorityLinkMapper {

  InstanceLinkDto convertToDto(InstanceAuthorityLink source);

  default InstanceLinkDtoCollection convertToDto(List<InstanceAuthorityLink> source) {
    var convertedLinks = source.stream().map(this::convertToDto).toList();

    return new InstanceLinkDtoCollection()
      .links(convertedLinks)
      .totalRecords(source.size());
  }

  InstanceAuthorityLink convertDto(InstanceLinkDto source);

  List<InstanceAuthorityLink> convertDto(List<InstanceLinkDto> source);

  default List<LinksCountDto> convert(Map<UUID, Long> source) {
    return source.entrySet().stream()
      .map(e -> new LinksCountDto().id(e.getKey()).totalLinks(e.getValue()))
      .toList();
  }
}
